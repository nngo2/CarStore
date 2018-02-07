package com.example.dao;

import com.example.model.User;

public interface UserDao {
    void addUser(User u);
    boolean isUserExisted(User u);
    boolean isUserNameExisted(String name);
}
