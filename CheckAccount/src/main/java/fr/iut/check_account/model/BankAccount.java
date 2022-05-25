package fr.iut.check_account.model;

public class BankAccount {
    private long id;

    private String name;

    private String surname;

    private double account;

    private Risk risk;

    public BankAccount() {
    }

    public BankAccount(long uuid, String name, String surname, double account, Risk risk) {
        this.id = uuid;
        this.name = name;
        this.surname = surname;
        this.account = account;
        this.risk = risk;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public Risk getRisk() {
        return risk;
    }

    public void setRisk(Risk risk) {
        this.risk = risk;
    }
}
