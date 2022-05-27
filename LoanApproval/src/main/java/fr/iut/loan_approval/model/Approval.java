package fr.iut.loan_approval.model;


import java.util.UUID;

public class Approval {
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
