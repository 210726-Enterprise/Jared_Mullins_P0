package com.revature.service;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;

public interface AccountService {

    boolean createAccount(User user, String accountType, String accountName);

    Account getAccountByAccountNumber(int accountNumber);

    void updateAccount();

    boolean deleteAccount(int accountNumber);

    RevArrayList<Account> getAccountByUserId(int userId);

    boolean makeDeposit(int accountNumber, double depositAmount);

    boolean makeWithdrawal(int accountNumber, double wAmount);

    double getBalanceByAccountNumber(int accountNumber);

    RevArrayList<Transaction> getTransactionsByAccountNumber(int accountNumber);

    boolean addJointUser(String username, int accountNumber);

    boolean transferFunds(double transferAmount, int transferFromAccountNumber, int transferToAccountNumber);
}
