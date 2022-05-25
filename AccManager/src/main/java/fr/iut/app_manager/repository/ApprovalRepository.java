package fr.iut.app_manager.repository;

import fr.iut.acc_manager.exceptions.BankError;
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
            throw new BankError("No entities found", HttpStatus.NOT_FOUND, e);
        }
    }

    public void addApproval(Approval approval) {
        try{
            ofy().save().entity(approval).now();
        }catch (Exception e) {
            throw new BankError("Unable to Add", HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    public void deleteApproval(Approval approval) {
        try{
            ofy().delete().entity(approval).now();
        }catch (Exception e) {
            throw new BankError("Unable to delete", HttpStatus.NOT_FOUND);
        }
    }

    public Approval getOneApprovalPerUuid(UUID uuid) {
        try{
            return ofy().load().type(Approval.class).id(uuid.toString()).now();
        }catch (Exception e) {
            throw new BankError("No entitiy found", HttpStatus.NOT_FOUND);
        }
    }

    public Approval getOneApprovalPerIdAccount(long idAccount) {
        try{
            return ofy().load().type(Approval.class).id(idAccount).now();
        }catch (Exception e) {
            throw new BankError("No entitiy found", HttpStatus.NOT_FOUND);
        }
    }
}
