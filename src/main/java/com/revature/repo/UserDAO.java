package com.revature.repo;

import com.revature.collection.RevArrayList;
import com.revature.model.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO{

    /**
     * inserts a new record into the users table
     * @param user user object of the user to be inserted into the table
     */
    public void insertUser(User user) {
        String sql = "INSERT INTO users (username, password) values (?, ?)";

        try (Connection conn = ConnectionFactory.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * retrieves a user based on the given username
     * @param username username used to retrieve a user from the database
     * @return User object matching the username
     */
    public User selectUserByUsername(String username) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ?";

        try (Connection conn = ConnectionFactory.connect(); PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                user = new User(rs.getInt("user_id"), rs.getString("username"), rs.getString("password"));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return user;
    }

    /**
     * retrieves all users from the users table
     * @return RevArrayList of user objects representing the whole users table
     */
    public RevArrayList<User> selectAllUsers() {
        RevArrayList<User> allUsers = new RevArrayList<>();;
        String sql = "SELECT * FROM users";

        try (Connection conn = ConnectionFactory.connect(); PreparedStatement ps = conn.prepareStatement(sql);) {
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                allUsers.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("username"),
                        rs.getString("password")
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return allUsers;
    }

    /**
     * Deletes a record from the user table based on the given user object
     * @param user user object to be deleted from the user table
     */
    public void deleteUser(User user) {

        String sql = "DELETE FROM users WHERE user_id = ?";
        try (Connection conn = ConnectionFactory.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, user.getUserId());
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
