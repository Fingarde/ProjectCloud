package fr.iut.check_account.model;

import fr.iut.utils.IdGenerator;

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
