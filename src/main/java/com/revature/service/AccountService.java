package com.revature.service;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.User;

public interface AccountService {

    void createAccount(User user, String accountType);

    Account getAccount();

    void updateAccount();

    void deleteAccount();

    RevArrayList<Account> getAccountByUser(User user);

    void makeDeposit(Account account, double depositAmount);

    void makeWithdrawal(Account account, double wAmount);

    double getBalanceByAccount(Account account);
}
