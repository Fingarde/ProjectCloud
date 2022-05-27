package fr.iut.app_manager.model;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

import java.util.UUID;

@Entity
public class Approval {
    public enum Response {
        ACCEPTED,
        REFUSED
    }

    @Id
    private long idAccount;

    private Response response;

    public Approval(){
    }

    public Approval(long idAccount, Response response) {
        this.idAccount = idAccount;
        this.response = response;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
