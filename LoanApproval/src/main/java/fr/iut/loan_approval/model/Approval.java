package fr.iut.loan_approval.model;


import java.util.UUID;

public class Approval {
    private String uuid;
    private long idAccount;
    private Response response;

    public Approval(){
        uuid = UUID.randomUUID().toString();
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
