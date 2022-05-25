package fr.iut.app_manager.sanitizers;

import fr.iut.acc_manager.model.BankAccount;
import fr.iut.app_manager.model.Approval;

public class ApprovalSanitizer {

    public static boolean verifyApproval(Approval approval) {
        return approval.getResponse()!=null;
    }

}
