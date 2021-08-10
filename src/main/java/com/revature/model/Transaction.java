package com.revature.model;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private double transactionAmount;
    private String type;
    private int accountNumber;
    private Date transactionDate;

    public Transaction() {
    }

    public Transaction(int transactionId, double transactionAmount, String type, int accountNumber, Date transactionDate) {
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.type = type;
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionAmount=" + transactionAmount +
                ", type='" + type + '\'' +
                ", accountNumber=" + accountNumber +
                '}';
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
