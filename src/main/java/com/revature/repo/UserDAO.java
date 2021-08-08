package com.revature.repo;

import com.revature.collection.RevArrayList;
import com.revature.model.User;

public interface UserDAO {

    //CREATE
    void insertUser(User user);

    //READ
    User selectUserByUsername(String username);
    User selectUserByAccountNumber();
    RevArrayList<User> selectAllUsers();

    //UPDATE
    void updateUser();

    //DELETE
    void deleteUser(User user);
}
