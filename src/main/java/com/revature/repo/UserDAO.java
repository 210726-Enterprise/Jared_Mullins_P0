package com.revature.repo;

import com.revature.model.User;

public interface UserDAO {

    //CREATE
    void insertUser();

    //READ
    User selectUser();

    //UPDATE
    void updateUser();

    //DELETE
    void deleteUser();
}
