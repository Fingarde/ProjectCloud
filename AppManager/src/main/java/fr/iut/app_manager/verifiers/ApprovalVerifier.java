package fr.iut.app_manager.verifiers;

import fr.iut.app_manager.model.Approval;

public class ApprovalVerifier {

    public static boolean verifyApproval(Approval approval) {
        return approval.getResponse()!=null;
    }

}
