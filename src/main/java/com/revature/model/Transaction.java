package com.revature.model;

import java.util.Date;

public class Transaction {
    private int transactionId;
    private double transactionAmount;
    private String note;
    private int accountNumber;
    private Date transactionDate;

    public Transaction() {
    }

    public Transaction(int transactionId, double transactionAmount, String note, int accountNumber) {
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.note = note;
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionAmount=" + transactionAmount +
                ", note='" + note + '\'' +
                ", accountNumber=" + accountNumber +
                '}';
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
}
