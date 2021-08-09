package com.revature.repo;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.User;

public interface AccountDAO {

    //CREATE
    boolean insertAccount(User user, String accountType);

    //READ
    Account selectAccountByPrimary(User user);

    //UPDATE
    void updateAccount();

    //DELETE
    void deleteAccount();

    RevArrayList<Account> selectAccountByUserId(int userId);

    void updateBalance(int accountNumber, double withdrawalOrDepositAmount);

    double selectBalanceByAccountNumber(int accountNumber);

    Account selectAccountByAccountNumber(int accountNumber);
}
