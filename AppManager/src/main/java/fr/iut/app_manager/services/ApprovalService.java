package fr.iut.app_manager.services;

import fr.iut.app_manager.exceptions.ApprovalError;
import fr.iut.app_manager.model.Approval;
import fr.iut.app_manager.repository.ApprovalRepository;
import fr.iut.app_manager.verifiers.ApprovalVerifier;
import fr.iut.error.Error;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Service
public class ApprovalService {
    @Autowired
    private ApprovalRepository approvalRepository;

    public List<Approval> getAllApproval() {
        try {
            return approvalRepository.getAllApproval();
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            e.printStackTrace();
            throw new ApprovalError("Error fetching approvals", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    public void addApproval(Approval approval) {
        if (!ApprovalVerifier.verifyApproval(approval)) {
            throw new ApprovalError("Error approval is not valid", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            approvalRepository.addApproval(approval);
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            throw new ApprovalError("Error adding approvals", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    public void deleteApproval(Approval approval) {
        try {
            approvalRepository.deleteApproval(approval);
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            e.printStackTrace();
            throw new ApprovalError("Error deleting approval", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    public Approval getApprovalPerIdAccount(long idAccount) {
        try {
            return approvalRepository.getApprovalPerIdAccount(idAccount);
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            e.printStackTrace();
            throw new ApprovalError("Error fetching approval", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    public void modifyBankAccount(Approval approval) {
        if (!ApprovalVerifier.verifyApproval(approval)){
            throw new ApprovalError("Error bank account is not valid", HttpStatus.UNPROCESSABLE_ENTITY);
        }
        try {
            approvalRepository.modifyApproval(approval);
        } catch (Exception e) {
            if(e instanceof Error) {
                System.err.println(e.getMessage());
                if (((Error) e).getBase() != null) ((Error) e).getBase().printStackTrace();
                throw e;
            }

            e.printStackTrace();
            throw new ApprovalError("Error modify account", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }
}
