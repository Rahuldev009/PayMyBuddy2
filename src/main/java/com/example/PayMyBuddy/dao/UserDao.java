package com.example.PayMyBuddy.dao;

import com.example.PayMyBuddy.model.User;

import java.util.List;

public interface UserDao {

    public List<User> getAllUsers();

    public User getUser(int id);

    public User addUser(User user);

    public User updateUser(User user);

    public User deleteUser(int id);

    public User findByEmail(String email);

}
