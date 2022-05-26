package fr.iut.app_manager.model;

import com.googlecode.objectify.annotation.Id;

import java.util.UUID;

public class Approval {
    enum Response {
        ACCEPTED,
        REFUSED
    }

    @Id
    private String uuid;

    private long idAccount;

    private Response response;

    public Approval(){
        uuid = UUID.randomUUID().toString();
        response = null;
    }

    public Approval(UUID uuid, long idAccount, Response response) {
        this.uuid = uuid.toString();
        this.idAccount = idAccount;
        this.response = response;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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
