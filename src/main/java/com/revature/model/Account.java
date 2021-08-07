package com.revature.model;

public class Account {
    private int accountNumber;
    private double balance;
    private final String type;

    public Account(int accountNumber, double balance, String type) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                '}';
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
