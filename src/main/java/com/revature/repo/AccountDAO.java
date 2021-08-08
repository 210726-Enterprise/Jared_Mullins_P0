package com.revature.repo;

import com.revature.model.Account;
import com.revature.model.User;

public interface AccountDAO {

    //CREATE
    void insertAccount(User user, String accountType);

    //READ
    Account selectAccountByPrimary(User user);

    //UPDATE
    void updateAccount();

    //DELETE
    void deleteAccount();
}
