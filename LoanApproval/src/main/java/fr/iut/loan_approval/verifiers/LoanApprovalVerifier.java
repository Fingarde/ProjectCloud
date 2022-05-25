package fr.iut.loan_approval.verifiers;

import fr.iut.loan_approval.model.LoanApproval;

public class LoanApprovalVerifier {

    private static boolean isNullEmpty(String str) {
        return str == null || str.equals("");
    }

    public static boolean verifyLoanApproval(LoanApproval loanApproval) {
        // TODO VERIFY ACCOUNT_ID
        return loanApproval.getAmount() >= 0.0;
    }
}
