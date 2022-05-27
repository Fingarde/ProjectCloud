package fr.iut.app_manager.repository;

import fr.iut.app_manager.exceptions.ApprovalError;
import fr.iut.app_manager.model.Approval;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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

    public Approval addApproval(Approval approval) {
        try{
            ofy().save().entity(approval).now();
            return approval;
        }catch (Exception e) {
            throw new ApprovalError("Unable to Add", HttpStatus.UNPROCESSABLE_ENTITY,e);
        }
    }

    public Approval deleteApproval(Approval approval) {
        try{
            ofy().delete().entity(approval).now();
            return approval;
        }catch (Exception e) {
            throw new ApprovalError("Unable to delete", HttpStatus.NOT_FOUND,e);
        }
    }

    public Approval getApprovalPerIdAccount(long idAccount) {
        try{
            Approval approval = ofy().load().type(Approval.class).id(idAccount).now();
            if (approval==null) {
                throw new ApprovalError("No entitiy found", HttpStatus.NOT_FOUND);
            }
            return approval;
        }catch (Exception e) {
            throw new ApprovalError("No entitiy found", HttpStatus.NOT_FOUND,e);
        }
    }

    public Approval modifyApproval(Approval approval) {
        try{
            ofy().save().entity(approval).now();
            return approval;
        }catch (Exception e) {
            throw new ApprovalError("Unable to Modify", HttpStatus.UNPROCESSABLE_ENTITY, e);
        }
    }
}
