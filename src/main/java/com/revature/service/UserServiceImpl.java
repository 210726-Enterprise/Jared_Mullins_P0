package com.revature.service;

import com.revature.collection.RevArrayList;
import com.revature.model.User;
import com.revature.repo.UserDAO;
import com.revature.repo.UserDAOImpl;


public class UserServiceImpl implements UserService{

    private UserDAO uDao;

    public UserServiceImpl() {
        uDao = new UserDAOImpl();
    }

    //CREATE

    @Override
    public boolean createUser(User user) {
        uDao.insertUser(user);
        return true;
    }

    //READ

    @Override
    public User getUserByUsername(String username) {
        return uDao.selectUserByUsername(username);
    }

    @Override
    public User getUserByAccountNumber(int accountNumber) {
        return null;
    }

    @Override
    public RevArrayList<User> getAllUsers() {
        return uDao.selectAllUsers();
    }

    //UPDATE

    @Override
    public void updateUser() {

    }

    //DELETE

    @Override
    public void deleteUser(User user) {
        uDao.deleteUser(user);
    }
}
