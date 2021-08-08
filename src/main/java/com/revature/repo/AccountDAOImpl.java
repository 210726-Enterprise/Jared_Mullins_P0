package com.revature.repo;

import com.revature.collection.RevArrayList;
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

    @Override
    public RevArrayList<Account> selectAccountByUser(User user) {
        RevArrayList<Account> accounts = new RevArrayList<>();
        Connection conn = ConnectionFactory.connect();
        String sql = "SELECT * FROM accounts WHERE account_number IN (SELECT account_number FROM users_accounts WHERE user_id = ?)";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                accounts.add(new Account(
                        rs.getInt("account_number"),
                        rs.getInt("balance"),
                        rs.getString("account_type"),
                        rs.getInt("account_primary")
                ));
            }

            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }

    @Override
    public void updateBalance(Account account, double withdrawalOrDepositAmount) {
        Connection conn = ConnectionFactory.connect();
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        try {
            //TODO URGENT figure out why this is rounding to nearest dollar
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1, selectBalanceByAccount(account) + withdrawalOrDepositAmount);
            ps.setInt(2, account.getAccountNumber());
            ps.execute();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public double selectBalanceByAccount(Account account) {
        double balance = 0;
        Connection conn = ConnectionFactory.connect();
        String sql = "SELECT balance FROM accounts WHERE account_number = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, account.getAccountNumber());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                balance = rs.getDouble("balance");
            }

            conn.close();
            return balance;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //TODO figure out a better bad return
        return -1;
    }
}
