package com.revature.service;

import com.revature.model.Account;
import com.revature.model.User;

public interface AccountService {

    void createAccount(User user, String accountType);

    Account getAccount();

    void updateAccount();

    void deleteAccount();

}
