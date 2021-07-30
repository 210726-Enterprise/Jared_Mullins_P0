package net.revature.utils;

import net.revature.model.User;

public class UserStorage {
    private int size = 0;
    private int capacity = 2;
    private User[] users = new User[capacity];

    public void add(User user) {
        users[size++] = user;
        if(size == capacity) {
            this.expandArray();
        }
    }

    private void expandArray() {
        User[] oldArray = users;
        capacity *= 2;
        User[] newArray = new User[capacity];
        for(int i = 0; i < oldArray.length; i++) {
            newArray[i] = oldArray[i];
        }
        users = newArray;
    }

    public User getUserByUsername(String username) {
        for(int i = 0; i < this.size(); i++) {
            if (this.getUser(i).getUsername().equals(username)) {
                return users[i];
            }
        }
        return null;
    }

    public User getUser(int index) {
        return users[index];
    }

    public int size() {
        return size;
    }

    public int length() {
        return capacity;
    }

    public boolean contains(String input) {
        for(int i = 0; i < this.size(); i++) {
            if (this.getUser(i).getUsername().equals(input)) {
                return true;
            }
        }
        return false;
    }
}
