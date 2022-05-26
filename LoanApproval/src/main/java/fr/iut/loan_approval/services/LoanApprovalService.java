package fr.iut.loan_approval.services;

import fr.iut.config.Config;
import fr.iut.loan_approval.model.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoanApprovalService {
    private RestTemplate template = new RestTemplate();

    public String addLoanApproval(LoanApproval loanApproval) {
        Risk risk = template.getForObject(Config.CHECK_ACCOUNT_URL + "/checkaccount/"+loanApproval.getAccountId(), Risk.class);

        if (risk == Risk.HIGH) {
            Response responseRisk = addApproval(loanApproval.getAccountId());
            if (responseRisk == Response.ACCEPTED) {
                double resp = addAmount(loanApproval);
                return "Approved : "+resp;
            }else {
                return "Refused";
            }
        } else {
            if (loanApproval.getAmount() >= 10000) {
                Response responseBigAmount = addApproval(loanApproval.getAccountId());
                if (responseBigAmount == Response.ACCEPTED) {
                    double resp = addAmount(loanApproval);
                    return "Approved"+resp;
                } else {
                    return "Refused";
                }
            } else {
                double resp = addAmount(loanApproval);
                return "Approved : "+resp;
            }
        }
    }

    private Response addApproval (long idAccount) {
        try {
            Approval appr = template.getForObject(Config.APP_MANAGER_URL + "/approval/account/"+idAccount, Approval.class);
            return appr.getResponse();
        } catch (Exception e) {
            Approval approval = new Approval();
            approval.setIdAccount(idAccount);
            approval.setResponse(Response.REFUSED);
            ResponseEntity<Approval> result = template.postForEntity(Config.APP_MANAGER_URL + "/approval", approval, Approval.class);
            return result.getBody().getResponse();
        }
    }

    private double addAmount (LoanApproval loanApproval) {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(loanApproval.getAccountId());
        bankAccount.setAccount(loanApproval.getAmount());

        HttpEntity<BankAccount> requestBody = new HttpEntity<>(bankAccount);

        ResponseEntity<BankAccount> result = template.exchange(Config.ACC_MANAGER_URL + "/bankaccount/"+loanApproval.getAccountId(), HttpMethod.PUT, requestBody, BankAccount.class);
        return result.getBody().getAccount();
    }

}
