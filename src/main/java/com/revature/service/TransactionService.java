package com.revature.service;

import com.revature.model.Transaction;

public interface TransactionService {

    void createTransaction();

    Transaction getTransaction();

    void updateTransaction();

    void deleteTransaction();

}
