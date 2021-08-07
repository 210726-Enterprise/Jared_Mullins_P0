package com.revature.repo;

import com.revature.model.Account;

public interface AccountDAO {

    //CREATE
    void insertAccount();

    //READ
    Account selectAccount();

    //UPDATE
    void updateAccount();

    //DELETE
    void deleteAccount();
}
