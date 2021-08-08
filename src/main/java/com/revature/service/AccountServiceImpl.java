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
    public void makeDeposit(Account account, double depositAmount) {
        if(depositAmount <= 0) {
            System.out.println("Invalid deposit amount");
            System.out.println("Please enter an amount greater than $0.00");
        }
        aDAO.updateBalance(account, depositAmount);
    }

    @Override
    public void makeWithdrawal(Account account, double wAmount) {
        if(wAmount > account.getBalance()) {
            System.out.println("Insufficient Funds");
            System.out.printf("Current Balance: $%,.2f %n", getBalanceByAccount(account));
            return;
        }
        wAmount *= -1;
        aDAO.updateBalance(account, wAmount);
    }

    @Override
    public double getBalanceByAccount(Account account) {
        return aDAO.selectBalanceByAccount(account);
    }
}
