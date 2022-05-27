package fr.iut.loan_approval.services;

import fr.iut.config.Config;
import fr.iut.error.Error;
import fr.iut.loan_approval.exceptions.LoanApprovalError;
import fr.iut.loan_approval.model.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class LoanApprovalService {
    private RestTemplate template = new RestTemplate();

    public LoanResponse addLoanApproval(LoanApproval loanApproval) {
        Risk risk;

        try {
            risk = template.getForObject(Config.CHECK_ACCOUNT_URL + "/checkaccount/"+loanApproval.getAccountId(), Risk.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoanApprovalError("No account found", HttpStatus.NOT_FOUND,e);
        }

        if (loanApproval.getAmount() >= 10000 || risk == Risk.HIGH) {
            Response responseBigAmount = addApproval(loanApproval.getAccountId());
            if (responseBigAmount == Response.ACCEPTED) {
                BankAccount resp = addAmount(loanApproval);
                return new LoanResponse(resp,responseBigAmount);
            } else {
                BankAccount bankAccount;
                try {
                    bankAccount = template.getForObject(Config.ACC_MANAGER_URL + "/bankaccount/"+loanApproval.getAccountId(), BankAccount.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new LoanApprovalError("No account found", HttpStatus.NOT_FOUND,e);
                }
                return new LoanResponse(bankAccount,responseBigAmount);
            }
        } else {
            BankAccount resp = addAmount(loanApproval);
            return new LoanResponse(resp,Response.ACCEPTED);
        }

    }

    private Response addApproval (long idAccount) {
        try {
            Approval appr = template.getForObject(Config.APP_MANAGER_URL + "/approval/"+idAccount, Approval.class);
            return appr.getResponse();
        } catch (Exception e1) {
            Approval approval = new Approval();
            approval.setIdAccount(idAccount);
            approval.setResponse(Response.REFUSED);
            try {
                Approval result = template.postForObject(Config.APP_MANAGER_URL + "/approval", approval, Approval.class);
                return result.getResponse();
            } catch (Exception e2) {
                e2.printStackTrace();
                throw new LoanApprovalError("Error modify approval", HttpStatus.UNPROCESSABLE_ENTITY,e2);
            }
        }
    }

    private BankAccount addAmount (LoanApproval loanApproval) {
        BankAccount actualAccount;
        try {
            actualAccount = template.getForObject(Config.ACC_MANAGER_URL + "/bankaccount/"+loanApproval.getAccountId(), BankAccount.class);
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoanApprovalError("No account found", HttpStatus.NOT_FOUND,e);
        }

        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccount(loanApproval.getAmount()+actualAccount.getAccount());

        HttpEntity<BankAccount> requestBody = new HttpEntity<>(bankAccount);

        try {
            ResponseEntity<BankAccount> result = template.exchange(Config.ACC_MANAGER_URL + "/bankaccount/"+loanApproval.getAccountId(),HttpMethod.PUT, requestBody, BankAccount.class);
            return result.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            throw new LoanApprovalError("Error modify account", HttpStatus.UNPROCESSABLE_ENTITY,e);
        }
    }

}
