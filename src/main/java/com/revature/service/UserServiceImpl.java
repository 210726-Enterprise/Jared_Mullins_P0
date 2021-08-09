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
    public boolean createUser(String username, String password) {
        RevArrayList<User> allUsers = getAllUsers();

        for(int i = 0; i < allUsers.size(); i++) {
            if(username.equals(allUsers.get(i).getUsername())) {
                System.out.println("\nSorry, that username is already taken.");
                return false;
            }
        }
        User newUser = new User(username, password);
        uDao.insertUser(newUser);
        return true;
    }

    @Override
    public User verifyUser(String username, String password) {
        RevArrayList<User> allUsers = getAllUsers();
        User user = uDao.selectUserByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            //TODO cleanup feedback
            System.out.println("Incorrect username or password");
        }
        return null;
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
