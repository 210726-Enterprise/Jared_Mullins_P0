package com.revature.model;

public class Account {
    private int accountNumber;
    private double balance;
    private String type;
    private int accountPrimary;

    public Account() {
    }

    public Account(int accountNumber, double balance, String type, int accountPrimary) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.accountPrimary = accountPrimary;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                '}';
    }

    public int getAccountPrimary() {
        return accountPrimary;
    }

    public void setAccountPrimary(int accountPrimary) {
        this.accountPrimary = accountPrimary;
    }

    public String getType() {
        return type;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
