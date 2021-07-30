package net.revature.model;

public class Account {
    private int accountNumber;
    private double balance = 0;

    public Account() {
        this.accountNumber = (int) Math.floor(Math.random() * (999 - 100) + 100);
        this.balance = balance;
    }

    public void makeDeposit(double depositAmount) {
        balance += depositAmount;
    }

    public void makeWithdrawal(double withdrawalAmount) {
        balance -= withdrawalAmount;
    }

    public double getBalance() {
        return balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
}
