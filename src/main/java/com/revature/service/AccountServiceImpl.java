package com.revature.service;

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
}
