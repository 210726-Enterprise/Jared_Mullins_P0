package com.revature.service;

import com.revature.collection.RevArrayList;
import com.revature.model.User;

public interface UserService {

    boolean createUser(User user);

    User getUserByUsername(String username);
    User getUserByAccountNumber(int accountNumber);
    RevArrayList<User> getAllUsers();

    void updateUser();

    void deleteUser(User user);
}
