package net.revature.model;

public class User {
    private String username;
    private String password;
    private String emailAddress;
    private Account account;
    private boolean isLoggedIn;

    public User(String username, String password, String emailAddress, Account account) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.account = account;
        this.isLoggedIn = false;
    }

    public void login() {
        isLoggedIn = true;
    }

    public void logout() {
        isLoggedIn = false;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public Account getAccount() {
        return account;
    }
}
