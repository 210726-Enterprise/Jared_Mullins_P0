package com.revature.service;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.repo.AccountDAO;

public class AccountService {

    /**
     * AccountDAO object for DAO layer logic
     */
    private AccountDAO aDAO = new AccountDAO();

    /**
     * Passes a user, account type, and account name to the DAO layer
     * @param user owner of the new account
     * @param accountType type of the new account
     * @param accountName friendly name of new account (can be null)
     * @return true if account is successfully created; false otherwise
     */
    public boolean createAccount(User user, String accountType, String accountName) {
        return aDAO.insertAccount(user, accountType, accountName);
    }


    /**
     * passes account number of the account being deleted to the DAO layer
     * @param accountNumber account number of account to be deleted
     * @return true if successfully deleted account: false otherwise
     */
    public boolean deleteAccount(int accountNumber) {
        return aDAO.deleteAccount(accountNumber);
    }


    /**
     * passes the userId to the DAO layer to get accounts associated with it
     * @param userId userId of accounts being queried
     * @return revArrayList of accounts that belong to the user with the provided userId
     */
    public RevArrayList<Account> getAccountByUserId(int userId) {
        return aDAO.selectAccountByUserId(userId);
    }


    /**
     * passes account number and deposit amount to DAO layer
     * @param accountNumber account number of account being deposited into
     * @param depositAmount amount being deposited
     * @return true if deposit is successful; false otherwise
     */
    public boolean makeDeposit(int accountNumber, double depositAmount) {
        if(depositAmount <= 0) {
            System.out.println("\n*****");
            System.out.println("INVALID DEPOSIT AMOUNT");
            System.out.println("Please enter an amount greater than $0.00");
            System.out.println("*****");
            return false;
        }
        aDAO.updateBalance(accountNumber, depositAmount);
        return true;
    }


    /**
     * passes account number and withdrawal amount to DAO layer
     * @param accountNumber account number of account being withdrawn from
     * @param wAmount amount being withdrawn
     * @return true if withdrawal is successful; false otherwise
     */
    public boolean makeWithdrawal(int accountNumber, double wAmount) {
        if(wAmount > getBalanceByAccountNumber(accountNumber)) {
            System.out.println("\n*****");
            System.out.println("INSUFFICIENT FUNDS");
            System.out.printf("Current Balance: $%,.2f %n", getBalanceByAccountNumber(accountNumber));
            System.out.println("*****");
            return false;
        } else if (wAmount <= 0) {
            System.out.println("\n*****");
            System.out.println("INVALID WITHDRAWAL AMOUNT");
            System.out.println("Please enter an amount greater than $0.00");
            System.out.println("*****");
            return false;
        }
        wAmount *= -1;
        aDAO.updateBalance(accountNumber, wAmount);
        return true;
    }


    /**
     * passes account number of whose account balance will be queried to DAO layer
     * @param accountNumber account number whose balance will be queried
     * @return balance of account
     */
    public double getBalanceByAccountNumber(int accountNumber) {
        return aDAO.selectBalanceByAccountNumber(accountNumber);
    }


    /**
     * passes account number of account whose transactions are being queried to DAO layer
     * @param accountNumber account number whose transactions are being queried
     * @return revArrayList of all transactions associated with account
     */
    public RevArrayList<Transaction> getTransactionsByAccountNumber(int accountNumber) {
        return aDAO.selectTransactionByAccountNumber(accountNumber);
    }


    /**
     * passes username of user being added to account, and account number where the user should be added to DAO layer
     * @param username username of user to be added
     * @param accountNumber account number of account user will be added to
     * @return true if user is added successfully; false otherwise
     */
    public boolean addJointUser(String username, int accountNumber) {
        UserService userS = new UserService();
        RevArrayList<User> allUsers = userS.getAllUsers();
        User user = userS.getUserByUsername(username);
        for(int i = 0; i < allUsers.size(); i++) {
            if(username.equals(allUsers.get(i).getUsername()) && user != null) {
                return aDAO.insertJointAccountHolder(user.getUserId(), accountNumber);
            }
        }
        return false;
    }


    /**
     * passes transfer amount, account number of sending account, and account number of receiving account to DAO layer
     * @param transferAmount amount of transfer
     * @param transferFromAccountNumber sending accounts account number
     * @param transferToAccountNumber receiving accounts account number
     * @return true if transfer is successful; false otherwise
     */
    public boolean transferFunds(double transferAmount, int transferFromAccountNumber, int transferToAccountNumber) {
        if(transferAmount < 0) {
            System.out.println("\n*****");
            System.out.println("Transfer amount must be greater than $0.00");
            System.out.println("*****");
            return false;
        }

        if(transferAmount > getBalanceByAccountNumber(transferFromAccountNumber)) {
            System.out.println("\n*****");
            System.out.println("Insufficient funds for transfer.");
            System.out.printf("Current balance for account #" + transferFromAccountNumber + ": $%,.2f %n", getBalanceByAccountNumber(transferFromAccountNumber));
            System.out.println("*****");
            return false;
        }

        if(aDAO.selectAccountByAccountNumber(transferFromAccountNumber) != null && aDAO.selectAccountByAccountNumber(transferToAccountNumber) != null) {
            return aDAO.updateTransferAccounts(transferAmount, transferFromAccountNumber, transferToAccountNumber);
        }
        System.out.println("\n*****");
        System.out.println("Could not locate account for transfer");
        System.out.println("*****");
        return false;
    }
}
