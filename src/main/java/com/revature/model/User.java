package com.revature.model;

public class User {

    /**
     * Member variables match what is in database for user
     * userId = unique id number that identifies user
     * username = unique username associated with user
     * password = password associated with user
     */
    private int userId;
    private String username;
    private String password;


    /**
     * two arg constructor
     * @param username unique username number that identifies user
     * @param password password associated with user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * all arg constructor
     * @param userId unique id number that identifies user
     * @param username unique username associated with user
     * @param password password associated with user
     */
    public User(int userId, String username, String password) {
        this.userId = userId;
        this.username = username;
        this.password = password;
    }


    /**
     * gets userId
     * @return userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * gets username
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets username
     * @param username new username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets password
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * sets password
     * @param password new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * overridden toString method
     * @return string of user info
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
