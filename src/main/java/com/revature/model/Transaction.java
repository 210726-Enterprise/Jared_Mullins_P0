package com.revature.model;

import java.util.Date;

public class Transaction {
    /**
     * transaction fields match database attributes
     * transactionId = unique id number of transaction
     * transactionAmount = how much the transaction was
     * type = transaction type (deposit, withdrawal, transfer_in, transfer_out)
     * accountNumber = account associated with transaction
     * transactionDate = date of transaction
     */
    private final int transactionId;
    private final double transactionAmount;
    private final String type;
    private final int accountNumber;
    private final Date transactionDate;


    /**
     * Transaction constructor to initialize all member variables
     * @param transactionId unique id number of transaction
     * @param transactionAmount how much the transaction was
     * @param type transaction type (deposit, withdrawal, transfer_in, transfer_out)
     * @param accountNumber account associated with transaction
     * @param transactionDate date of transaction
     */
    public Transaction(int transactionId, double transactionAmount, String type, int accountNumber, Date transactionDate) {
        this.transactionId = transactionId;
        this.transactionAmount = transactionAmount;
        this.type = type;
        this.accountNumber = accountNumber;
        this.transactionDate = transactionDate;
    }

    /**
     * Overridden toString method
     * @return String of transaction info
     */
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionAmount=" + transactionAmount +
                ", type='" + type + '\'' +
                ", accountNumber=" + accountNumber +
                '}';
    }

    /**
     * gets date of transaction
     * @return transaction date
     */
    public Date getTransactionDate() {
        return transactionDate;
    }


    /**
     * gets transaction id
     * @return transaction id
     */
    public int getTransactionId() {
        return transactionId;
    }

    /**
     * gets amount of transaction
     * @return amount of transaction
     */
    public double getTransactionAmount() {
        return transactionAmount;
    }


    /**
     * gets transaction type
     * @return transaction type
     */
    public String getType() {
        return type;
    }

    /**
     * gets account number associated with transaction
     * @return account number associated with transaction
     */
    public int getAccountNumber() {
        return accountNumber;
    }
}
