package com.revature.repo;

import com.revature.model.Transaction;

public interface TransactionDAO {

    //CREATE
    void insertTransaction();

    //READ
    Transaction selectTransaction();

    //UPDATE
    void updateTransaction();

    //DELETE
    void deleteTransaction();

}
