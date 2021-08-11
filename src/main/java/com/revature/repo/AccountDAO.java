package com.revature.repo;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;

public interface AccountDAO {

    //CREATE
    boolean insertAccount(User user, String accountType, String accountName);

    //READ
    Account selectAccountByPrimary(User user);

    //UPDATE
    void updateAccount();

    //DELETE
    boolean deleteAccount(int accountNumber);

    RevArrayList<Account> selectAccountByUserId(int userId);

    void updateBalance(int accountNumber, double withdrawalOrDepositAmount);

    double selectBalanceByAccountNumber(int accountNumber);

    Account selectAccountByAccountNumber(int accountNumber);

    RevArrayList<Transaction> selectTransactionByAccountNumber(int accountNumber);

    boolean insertJointAccountHolder(int userId, int accountNumber);

    boolean updateTransferAccounts(double transferAmount, int transferFromAccountNumber, int transferToAccountNumber);
}
