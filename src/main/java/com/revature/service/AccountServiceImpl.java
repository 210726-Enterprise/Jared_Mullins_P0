package com.revature.service;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
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
    public void deleteAccount() {

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
}
