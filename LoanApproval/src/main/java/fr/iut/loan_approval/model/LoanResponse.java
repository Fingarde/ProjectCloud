package fr.iut.loan_approval.model;

public class LoanResponse {
    private BankAccount bankAccount;
    private String response;

    public LoanResponse() {
    }

    public LoanResponse(BankAccount bankAccount,String response) {
        this.bankAccount = bankAccount;
        this.response = response;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
