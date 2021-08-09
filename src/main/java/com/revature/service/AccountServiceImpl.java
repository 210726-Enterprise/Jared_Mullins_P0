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
    public void createAccount(User user, String accountType) {
        aDAO.insertAccount(user, accountType);
    }

    @Override
    public Account getAccount() {
        return null;
    }

    @Override
    public void updateAccount() {

    }

    @Override
    public void deleteAccount() {

    }

    @Override
    public RevArrayList<Account> getAccountByUser(User user) {
        return aDAO.selectAccountByUser(user);
    }

    @Override
    public boolean makeDeposit(Account account, double depositAmount) {
        if(depositAmount <= 0) {
            System.out.println("\n*****");
            System.out.println("INVALID DEPOSIT AMOUNT");
            System.out.println("Please enter an amount greater than $0.00");
            System.out.println("*****");
            return false;
        }
        aDAO.updateBalance(account, depositAmount);
        return true;
    }

    @Override
    public boolean makeWithdrawal(Account account, double wAmount) {
        if(wAmount > getBalanceByAccount(account)) {
            System.out.println("\n*****");
            System.out.println("INSUFFICIENT FUNDS");
            System.out.printf("Current Balance: $%,.2f %n", getBalanceByAccount(account));
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
        aDAO.updateBalance(account, wAmount);
        return true;
    }

    @Override
    public double getBalanceByAccount(Account account) {
        return aDAO.selectBalanceByAccount(account);
    }
}
