package fr.iut.loan_approval.services;

import fr.iut.config.Config;
import fr.iut.loan_approval.model.LoanApproval;
import fr.iut.loan_approval.model.Risk;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoanApprovalService {
    public void addLoanApproval(LoanApproval loanApproval) {
        RestTemplate template = new RestTemplate();


        if (loanApproval.getAmount() >= 10_000) {

        }
        else {
            Risk risk = template.getForObject(Config.CHECK_ACCOUNT_URL + "/risk", Risk.class, loanApproval.getAccountId());

            if (risk == Risk.HIGH) {

            }
        }



    }
}
