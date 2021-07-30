package net.revature.model;

public class Account {
    private int accountNumber;
    private int balance = 0;

    public Account() {
        this.accountNumber = (int) Math.floor(Math.random() * (999 - 100) + 100);
        this.balance = balance;
    }

    public int getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
