package fr.iut.loan_approval.services;

import fr.iut.loan_approval.model.Approval;
import fr.iut.loan_approval.repository.ApprovalRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class ApprovalService {
    @Autowired
    private ApprovalRepository approvalRepository;

    public List<Approval> getAllApproval() {
        try {
            return approvalRepository.getAllApproval();
        } catch (Exception e) {
            throw new BankAccountException("Erreur lors de la récupération des comptes");
        }
    }

    public void addApproval(Approval approval) {
        try {
            approvalRepository.addApproval(approval);
        } catch (Exception e) {
            throw new BankAccountException("Erreur dans l'ajout du compte");
        }
    }

    public void deleteApproval(Approval approval) {
        try {
            approvalRepository.deleteApproval(approval);
        } catch (Exception e) {
            throw new BankAccountException("Erreur dans la suppression du compte");
        }
    }

    public Approval getOnBankAccount(String suuid) {
        UUID uuid;
        try {
            uuid = UUID.fromString(suuid);
        } catch (Exception e) {
            throw new BankAccountException("UUID incorrect");
        }
        try {
            return approvalRepository.getOneApproval(uuid);
        } catch (Exception e) {
            throw new BankAccountException("Compte bancaire non existant");
        }
    }
}
