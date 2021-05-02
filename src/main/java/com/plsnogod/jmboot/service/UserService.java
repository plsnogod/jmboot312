package com.plsnogod.jmboot.service;

import com.plsnogod.jmboot.model.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);
    List<User> showAllUsers();
    void updateUser(User user);
    void deleteUser(long id);
    User getUserById(long id);
    User getUserByName(String name);
}

