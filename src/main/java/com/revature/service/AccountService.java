package com.revature.service;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.User;

public interface AccountService {

    boolean createAccount(User user, String accountType);

    Account getAccountByAccountNumber(int accountNumber);

    void updateAccount();

    void deleteAccount();

    RevArrayList<Account> getAccountByUserId(int userId);

    boolean makeDeposit(int accountNumber, double depositAmount);

    boolean makeWithdrawal(int accountNumber, double wAmount);

    double getBalanceByAccountNumber(int accountNumber);
}
