package com.revature.model;


/**
 * Account objects represent accounts from the database
 */
public class Account {
    /**
     * Member variables match database attributes
     * account number = account's unique identification number
     * balance = amount of money in the account
     * type = account type (Checking, Savings)
     * accountPrimary = userId of the primary account holder
     * accountName = optional name given for more friendly account identification
     */
    private final int accountNumber;
    private double balance;
    private String type;
    private int accountPrimary;
    private String accountName;


    /**
     * All-arg constructor for Account class
     * @param accountNumber account's unique identification number
     * @param balance amount of money in the account
     * @param type account type (Checking, Savings)
     * @param accountPrimary userId of the primary account holder
     * @param accountName optional name given for more friendly account identification
     */
    public Account(int accountNumber, double balance, String type, int accountPrimary, String accountName) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
        this.accountPrimary = accountPrimary;
        this.accountName = accountName;
    }


    /**
     * Overridden toString method for the Account class
     * @return String of account information
     */
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", type='" + type + '\'' +
                '}';
    }


    /**
     * Sets account type
     * @param type String account type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets friendly name of account
     * @return friendly name of account
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * sets friendly name of account
     * @param accountName new friendly name of account
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * gets userId of account primary
     * @return userId of account primary
     */
    public int getAccountPrimary() {
        return accountPrimary;
    }


    /**
     * updates account primary
     * @param accountPrimary userId of new account primary
     */
    public void setAccountPrimary(int accountPrimary) {
        this.accountPrimary = accountPrimary;
    }


    /**
     * gets account type
     * @return account type
     */
    public String getType() {
        return type;
    }


    /**
     * gets account number
     * @return account number
     */
    public int getAccountNumber() {
        return accountNumber;
    }


    /**
     * gets balance of account
     * @return account balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * sets balance of account
     * @param balance new account balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }
}
