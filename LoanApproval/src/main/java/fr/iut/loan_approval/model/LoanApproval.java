package fr.iut.loan_approval.model;

public class LoanApproval {

    private long accountId;

    private double amount;


    public LoanApproval() {
    }

    public LoanApproval(long accountId, double amount) {
        this.accountId = accountId;
        this.amount = amount;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
