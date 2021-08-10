package com.revature.service;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.repo.AccountDAO;
import com.revature.repo.AccountDAOImpl;

public class AccountServiceImpl implements AccountService {

    private AccountDAO aDAO;

    public AccountServiceImpl() {
        aDAO = new AccountDAOImpl();
    }

    @Override
    public boolean createAccount(User user, String accountType) {
        return aDAO.insertAccount(user, accountType);
    }

    @Override
    public Account getAccountByAccountNumber(int accountNumber) {
        return aDAO.selectAccountByAccountNumber(accountNumber);
    }

    @Override
    public void updateAccount() {

    }

    @Override
    public boolean deleteAccount(int accountNumber) {
        return aDAO.deleteAccount(accountNumber);
    }

    @Override
    public RevArrayList<Account> getAccountByUserId(int userId) {
        return aDAO.selectAccountByUserId(userId);
    }

    @Override
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

    @Override
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

    @Override
    public double getBalanceByAccountNumber(int accountNumber) {
        return aDAO.selectBalanceByAccountNumber(accountNumber);
    }

    @Override
    public RevArrayList<Transaction> getTransactionsByAccountNumber(int accountNumber) {
        return aDAO.selectTransactionByAccountNumber(accountNumber);
    }

    @Override
    public boolean addJointUser(String username, int accountNumber) {
        UserService userS = new UserServiceImpl();
        User user = userS.getUserByUsername(username);
        if(user != null) {
            return aDAO.insertJointAccountHolder(user.getUserId(), accountNumber);
        }
        return false;
    }

    @Override
    public boolean transferFunds(double transferAmount, int transferFromAccountNumber, int transferToAccountNumber) {
        if(transferAmount < 0) {
            System.out.println("Transfer amount must be greater than $0.00");
            return false;
        }
        if(aDAO.selectAccountByAccountNumber(transferFromAccountNumber) != null && aDAO.selectAccountByAccountNumber(transferToAccountNumber) != null) {
            return aDAO.updateTransferAccounts(transferAmount, transferFromAccountNumber, transferToAccountNumber);
        }
        System.out.println("Could not locate account for transfer");
        return false;
    }
}
