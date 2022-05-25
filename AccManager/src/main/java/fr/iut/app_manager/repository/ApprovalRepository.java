package fr.iut.loan_approval.repository;

import fr.iut.loan_approval.model.Approval;

import java.util.List;
import java.util.UUID;

import static com.googlecode.objectify.ObjectifyService.ofy;

public class ApprovalRepository {
    public List<Approval> getAllApproval() {
        return ofy().load().type(Approval.class).list();
    }

    public void addApproval(Approval approval) {
        ofy().save().entity(approval).now();
    }

    public void deleteApproval(Approval approval) {
        ofy().delete().entity(approval).now();
    }

    public Approval getOneApproval(UUID uuid) {
        return ofy().load().type(Approval.class).id(uuid.toString()).now();
    }
}
