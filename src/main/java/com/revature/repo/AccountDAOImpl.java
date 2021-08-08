package com.revature.repo;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAOImpl implements AccountDAO{

    @Override
    public void insertAccount(User user, String accountType) {
        Connection conn = ConnectionFactory.connect();
        String sql = "INSERT INTO accounts (balance, account_type, account_primary) values (0, ?, ?)";
        String sql2 = "INSERT INTO users_accounts (account_number, user_id) values (?, ?) ";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, accountType);
            ps.setInt(2, user.getUserId());
            ps.execute();

            PreparedStatement ps2 = conn.prepareStatement(sql2);
            ps2.setInt(1, selectAccountByPrimary(user).getAccountNumber());
            ps2.setInt(2, user.getUserId());
            ps2.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Account selectAccountByPrimary(User user) {
        Account account = null;
        Connection conn = ConnectionFactory.connect();
        String sql = "SELECT * FROM accounts WHERE account_primary = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                account = new Account(
                        rs.getInt("account_number"),
                        rs.getInt("balance"),
                        rs.getString("account_type"),
                        rs.getInt("account_primary")
                );
            }

            conn.close();

            return account;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateAccount() {

    }

    @Override
    public void deleteAccount() {

    }
}
