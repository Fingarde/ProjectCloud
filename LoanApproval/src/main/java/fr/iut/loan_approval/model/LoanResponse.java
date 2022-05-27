package fr.iut.loan_approval.model;

public class LoanResponse {
    private BankAccount bankAccount;
    private Response response;

    public LoanResponse() {
    }

    public LoanResponse(BankAccount bankAccount, Response response) {
        this.bankAccount = bankAccount;
        this.response = response;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
