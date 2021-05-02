package com.plsnogod.jmboot.service;

import com.plsnogod.jmboot.model.Role;
import com.plsnogod.jmboot.model.User;

import java.util.List;

public interface UserService {
    boolean addUser(User user);
    List<User> showAllUsers();
    void updateUser(User user, String [] role);
    void deleteUser(long id);
    User viewUser(long id);
    Role viewRole(long id);
    User getUserById(long id);
    User findByUserName(String userName);
}



