package com.revature.service;

import com.revature.collection.RevArrayList;
import com.revature.model.User;
import com.revature.repo.UserDAO;


public class UserService {

    /**
     * UserDAO object for DAO layer logic
     */
    private UserDAO uDao = new UserDAO();

    /**
     * passes username and password to the DAO layer to create a new user
     * @param username new user's usernmae
     * @param password new user's password
     * @return true if user is created; false otherwise
     */
    public boolean createUser(String username, String password) {
        RevArrayList<User> allUsers = getAllUsers();

        for(int i = 0; i < allUsers.size(); i++) {
            if(username.equals(allUsers.get(i).getUsername())) {
                System.out.println("\n*****");
                System.out.println("Sorry, that username is already taken.");
                System.out.println("*****");
                return false;
            }
        }
        User newUser = new User(username, password);
        uDao.insertUser(newUser);
        System.out.println("\n*****");
        System.out.println("Successfully created new user!");
        System.out.println("*****");
        return true;
    }

    /**
     * passes uername and password to DAO layer during login
     * verifies that the user exists and that they have entered the correct password
     * @param username username of user logging in
     * @param password password of user logging in
     * @return User object of logged in user if user is verified; null otherwise
     */
    public User verifyUser(String username, String password) {
        RevArrayList<User> allUsers = getAllUsers();
        User user = uDao.selectUserByUsername(username);
        if(user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            System.out.println("\n*****");
            System.out.println("Incorrect username or password");
            System.out.println("*****");
        }
        return null;
    }


    /**
     * passes username to DAO layer to find user in database
     * @param username username of user being queried
     * @return User if user is found; can be null
     */
    public User getUserByUsername(String username) {
        return uDao.selectUserByUsername(username);
    }


    /**
     * calls the DAO layer to find get all users
     * @return revArrayList of all users in the database
     */
    public RevArrayList<User> getAllUsers() {
        return uDao.selectAllUsers();
    }


    /**
     * passes User that is to be deleted to the DAO layer
     * @param user User object of user being deleted
     */
    public void deleteUser(User user) {
        uDao.deleteUser(user);
    }
}
