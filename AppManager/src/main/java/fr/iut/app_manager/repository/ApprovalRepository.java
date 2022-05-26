package fr.iut.app_manager.repository;

import fr.iut.app_manager.exceptions.ApprovalError;
import fr.iut.app_manager.model.Approval;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

import static com.googlecode.objectify.ObjectifyService.ofy;

@Repository
public class ApprovalRepository {
    public List<Approval> getAllApproval() {
        try{
            return ofy().load().type(Approval.class).list();
        }catch (Exception e) {
            throw new ApprovalError("No entities found", HttpStatus.NOT_FOUND, e);
        }
    }

    public void addApproval(Approval approval) {
        try{
            ofy().save().entity(approval).now();
        }catch (Exception e) {
            throw new ApprovalError("Unable to Add", HttpStatus.UNPROCESSABLE_ENTITY,e);
        }
    }

    public void deleteApproval(Approval approval) {
        try{
            ofy().delete().entity(approval).now();
        }catch (Exception e) {
            throw new ApprovalError("Unable to delete", HttpStatus.NOT_FOUND,e);
        }
    }

    public Approval getOneApprovalPerUuid(UUID uuid) {
        try{
            return ofy().load().type(Approval.class).id(uuid.toString()).now();
        }catch (Exception e) {
            throw new ApprovalError("No entitiy found", HttpStatus.NOT_FOUND,e);
        }
    }

    public List<Approval> getAllApprovalPerIdAccount(long idAccount) {
        try{
            List<Approval> approvals = (List<Approval>) getAllApproval().stream().filter(approval -> approval.getIdAccount() == idAccount);
            if (approvals.isEmpty()) {
                throw new Exception();
            }
            return approvals;
        }catch (Exception e) {
            throw new ApprovalError("No entitiy found", HttpStatus.NOT_FOUND,e);
        }
    }
}
