package net.revature;

import net.revature.model.Account;
import net.revature.model.User;
import net.revature.utils.UserStorage;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {

        UserStorage allUsers = new UserStorage();
        User jared = new User("jwmullins", "secret", "jwm@gmail.com", new Account());
        allUsers.add(jared);

        startBanking(allUsers, null);
        

//        User chelsea = new User("ckmullins", "secret", "ckm@gmail.com", new Account());
//

//        allUsers.add(chelsea);
//        allUsers.add(new User("Obed", "o", "o", new Account()));
//        allUsers.add(new User("Bumbo", "b", "b", new Account()));

    }

    public static void startBanking(UserStorage allUsers, User user) {
        User currentUser = user;

        if(currentUser == null) {
            System.out.println("MAIN MENU:");
            System.out.println("What would you like to do?");
            System.out.println("(1) Login");
            System.out.println("(2) Register a new account");
            System.out.println("(0) Exit");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    return;
                case 1:
                    currentUser = verifyUser(allUsers);
                    if (currentUser != null) {
                        currentUser.login();
                        System.out.println("Hello " + currentUser.getUsername());
                    }
                    break;
                case 2:
                    System.out.println("Registering new user");
                    registerNewUser();
                    break;
                default:
                    System.out.println("Unknown error");
            }

        } else {
            System.out.println("USER MENU:");
            System.out.println("What would you like to do?: ");
        }
        startBanking(allUsers, currentUser);
    }

    public static void registerNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = scanner.nextLine();
        System.out.println("Enter a password");
        String password = scanner.nextLine();
        System.out.println("Enter your email address");
        String email = scanner.nextLine();

        User newUser = new User(username, password, email, new Account());
        System.out.println("Successfully created new user! \n***");
        System.out.println("Username: " + newUser.getUsername());
        System.out.println("Email Address: " + newUser.getEmailAddress());
        System.out.println("Account Number: " + newUser.getAccount().getAccountNumber() + "\n***");
        scanner.close();
    }

    public static User verifyUser(UserStorage allUsers) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Username:");
        String username = scanner.nextLine();
        if(allUsers.contains(username)) {
            User user = allUsers.getUserByUsername(username);
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            if(user.getPassword().equals(password)) {
                return user;
            } else {
                System.out.println("***\nCould not verify user. Try again. \n***");
            }
        } else {
            System.out.println("***\nThat user does not exist \n***");
        }
        return null;

    }
}
