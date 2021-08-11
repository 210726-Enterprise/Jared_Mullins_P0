package com.revature.repo;

import com.revature.collection.RevArrayList;
import com.revature.model.Account;
import com.revature.model.Transaction;
import com.revature.model.User;
import com.revature.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;


public class AccountDAO {

    /**
     * Inserts new bank account into the "accounts" table
     * @param user primary of the new account
     * @param accountType type of new account
     * @param accountName friendly name of new account
     * @return true if account is created; false otherwise
     */
    public boolean insertAccount(User user, String accountType, String accountName) {

        String sql = "INSERT INTO accounts (balance, account_type, account_primary, account_name) values (0, ?, ?, ?)";
        String sql2 = "INSERT INTO users_accounts (account_number, user_id) values (?, ?) ";
        try (Connection conn = ConnectionFactory.connect(); PreparedStatement ps = conn.prepareStatement(sql); PreparedStatement ps2 = conn.prepareStatement(sql2)){
            ps.setString(1, accountType);
            ps.setInt(2, user.getUserId());
            ps.setString(3, accountName);
            ps.execute();

            ps2.setInt(1, selectAccountByPrimary(user).getAccountNumber());
            ps2.setInt(2, user.getUserId());
            ps2.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    /**
     * retrieves an account from the database that matches the userId of the primary account holder
     * @param user account primary's userId
     * @return Account of the primary
     */
    public Account selectAccountByPrimary(User user) {
        Account account = null;
        String sql = "SELECT * FROM accounts WHERE account_primary = ?";
        try (Connection conn = ConnectionFactory.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, user.getUserId());
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                account = new Account(
                        rs.getInt("account_number"),
                        rs.getInt("balance"),
                        rs.getString("account_type"),
                        rs.getInt("account_primary"),
                        rs.getString("account_name")
                );
            }

            return account;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    /**
     * deletes an account from the table by account number
     * @param accountNumber account number of the account being deleted
     * @return true if account is deleted; false otherwise
     */
    public boolean deleteAccount(int accountNumber) {

        String sql = "DELETE FROM accounts WHERE account_number = ?";
        try (Connection conn = ConnectionFactory.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountNumber);
            ps.executeUpdate();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    /**
     * retrieves all accounts associated with the userId from the database
     * @param userId userId of the user querying the database
     * @return RevArrayList of account objects that belong to the user
     */
    public RevArrayList<Account> selectAccountByUserId(int userId) {
        RevArrayList<Account> accounts = new RevArrayList<>();
        String sql = "SELECT * FROM accounts WHERE account_number IN (SELECT account_number FROM users_accounts WHERE user_id = ?)";
        try (Connection conn = ConnectionFactory.connect(); PreparedStatement ps = conn.prepareStatement(sql);) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                accounts.add(new Account(
                        rs.getInt("account_number"),
                        rs.getInt("balance"),
                        rs.getString("account_type"),
                        rs.getInt("account_primary"),
                        rs.getString("account_name")
                ));
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return accounts;
    }


    /**
     * Updates the balance for the given account number
     * @param accountNumber account number whose balance will be adjusted
     * @param withdrawalOrDepositAmount amount user will withdrawal or deposit (will be multiplied by -1 if a withdrawal)
     */
    public void updateBalance(int accountNumber, double withdrawalOrDepositAmount) {
        String type = "DEPOSIT";
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        if(withdrawalOrDepositAmount < 0) {
            type = "WITHDRAWAL";
        }
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        String sql2 = "INSERT INTO transactions(amount, type, account_number, transaction_date) VALUES (?, ?, ?, ?)";
        try (
                Connection conn = ConnectionFactory.connect();
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                ) {
            //TODO URGENT figure out why this is rounding to nearest dollar

            ps.setDouble(1, selectBalanceByAccountNumber(accountNumber) + withdrawalOrDepositAmount);
            ps.setInt(2, accountNumber);
            ps.execute();

            ps2.setDouble(1, withdrawalOrDepositAmount);
            ps2.setString(2, type);
            ps2.setInt(3, accountNumber);
            ps2.setDate(4, sqlDate);
            ps2.execute();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    /**
     * gets the balance of the account by the account number
     * @param accountNumber account number of account whose balance is being retrieved
     * @return balance of the account
     */
    public double selectBalanceByAccountNumber(int accountNumber) {
        double balance = 0;
        String sql = "SELECT balance FROM accounts WHERE account_number = ?";
        try (Connection conn = ConnectionFactory.connect(); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                balance = rs.getDouble("balance");
            }

            return balance;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //TODO figure out a better bad return
        return -1;
    }


    /**
     * Returns an Account object based on the account number
     * @param accountNumber number of the account being retrieved
     * @return Account object with the corresponding accountNumber
     */
    public Account selectAccountByAccountNumber(int accountNumber) {
        Account account = null;

        String sql = "SELECT * FROM accounts WHERE account_number = ?";
        try (Connection conn = ConnectionFactory.connect(); PreparedStatement ps = conn.prepareStatement(sql)){

            ps.setInt(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                account = new Account(
                        rs.getInt("account_number"),
                        rs.getInt("balance"),
                        rs.getString("account_type"),
                        rs.getInt("account_primary"),
                        rs.getString("account_name")
                );
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return account;
    }

    /**
     * retrieves all transaction associated with the given account number
     * @param accountNumber account number whose transactions will be retrieved
     * @return RevArrayList of transactions associated with the account number
     */
    public RevArrayList<Transaction> selectTransactionByAccountNumber(int accountNumber) {
        RevArrayList<Transaction> transactions = new RevArrayList<>();
        Connection conn = ConnectionFactory.connect();
        String sql = "SELECT * FROM transactions WHERE account_number = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, accountNumber);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                transactions.add(new Transaction(
                        rs.getInt("transaction_id"),
                        rs.getDouble("amount"),
                        rs.getString("type"),
                        rs.getInt("account_number"),
                        rs.getDate("transaction_date")
                ));
            }

            conn.close();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return transactions;
    }


    /**
     * adds a record to the users_accounts table connecting the given userId with the given account number
     * @param userId userId of the new joint account holder
     * @param accountNumber account number of the account the new joint user is being added to
     * @return true if successfully added joint user; false othewise
     */
    public boolean insertJointAccountHolder(int userId, int accountNumber) {
        String sql = "INSERT INTO users_accounts (account_number, user_id) VALUES (?, ?)";
        try (Connection conn = ConnectionFactory.connect();PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, accountNumber);
            ps.setInt(2, userId);
            ps.execute();
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    /**
     * Updates two accounts when a transfer takes place
     * @param transferAmount amount being transferred (multiplied by -1 for the sending account)
     * @param transferFromAccountNumber account number of the sending account
     * @param transferToAccountNumber account number of the receiving account
     * @return true if transfer is successful; false otherwise
     */
    public boolean updateTransferAccounts(double transferAmount, int transferFromAccountNumber, int transferToAccountNumber) {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        String sql = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        String sql2 = "UPDATE accounts SET balance = ? WHERE account_number = ?";
        String sql3 = "INSERT INTO transactions(amount, type, account_number, transaction_date) VALUES (?, ?, ?, ?), (?,?,?,?)";
        try (
                Connection conn = ConnectionFactory.connect();
                PreparedStatement ps = conn.prepareStatement(sql);
                PreparedStatement ps2 = conn.prepareStatement(sql2);
                PreparedStatement ps3 = conn.prepareStatement(sql3);
                ) {

            ps.setDouble(1, selectBalanceByAccountNumber(transferFromAccountNumber) + (transferAmount * -1));
            ps.setInt(2, transferFromAccountNumber);
            ps.executeUpdate();


            ps2.setDouble(1, selectBalanceByAccountNumber(transferToAccountNumber) + (transferAmount));
            ps2.setInt(2, transferToAccountNumber);
            ps2.executeUpdate();


            ps3.setDouble(1, transferAmount * -1);
            ps3.setString(2,"TRANSFER_OUT");
            ps3.setInt(3, transferFromAccountNumber);
            ps3.setDate(4, sqlDate);

            ps3.setDouble(5, transferAmount);
            ps3.setString(6, "TRANSFER_IN");
            ps3.setInt(7, transferToAccountNumber);
            ps3.setDate(8, sqlDate);
            ps3.execute();

            System.out.println("\n*****");
            System.out.printf("Successfully transferred $%,.2f" + " from " + transferFromAccountNumber + " to " + transferToAccountNumber + "%n", transferAmount);
            System.out.printf("Account #" + transferFromAccountNumber + " new balance: $%,.2f %n", selectBalanceByAccountNumber(transferFromAccountNumber));
            System.out.println("*****\n");
            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
