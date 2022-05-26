package fr.iut.loan_approval.services;

import fr.iut.config.Config;
import fr.iut.loan_approval.model.Approval;
import fr.iut.loan_approval.model.LoanApproval;
import fr.iut.loan_approval.model.Response;
import fr.iut.loan_approval.model.Risk;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoanApprovalService {
    private RestTemplate template = new RestTemplate();

    public void addLoanApproval(LoanApproval loanApproval) {
        Risk risk = template.getForObject(Config.CHECK_ACCOUNT_URL + "/checkaccount/", Risk.class, loanApproval.getAccountId());
        if (risk == Risk.HIGH) {
            addApproval(loanApproval.getAccountId());
        }

        if (loanApproval.getAmount() >= 10000) {
            addApproval(loanApproval.getAccountId());
        } else {
            //Interroger le CheckAccount

        }



    }

    private void addApproval (long idAccount) {
        Approval approval = new Approval();
        approval.setIdAccount(idAccount);
        ResponseEntity<Approval> result = template.postForEntity(Config.APP_MANAGER_URL + "/approval", approval, Approval.class);
    }

    private void addAmount (LoanApproval loanApproval) {

    }

}
