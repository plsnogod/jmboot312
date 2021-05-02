package com.plsnogod.jmboot.service;

import com.plsnogod.jmboot.dao.RoleRepository;
import com.plsnogod.jmboot.model.Role;
import com.plsnogod.jmboot.model.User;
import com.plsnogod.jmboot.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements  UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    @Transactional
    public boolean addUser(User user) {
        User user1 = userRepository.getUserByName(user.getName());
        if (user1 != null) {
            return false;
        }
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);

        return true;
    }

    @Override
    @Transactional
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User viewUser(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.get();
    }

    @Override
    @Transactional
    public Role viewRole(long id) {
        Optional<Role> optionalUser = roleRepository.findById(id);

        return optionalUser.get();
    }

    @Override
    @Transactional
    public void updateUser(User user, String[] role) {
        Set<Role> rols = new HashSet<>();
        for (String s : role) {
            if (s.equals("ROLE_ADMIN")) {
                rols.add(viewRole(1));
            } else {
                rols.add(viewRole(2));
            }
        }
        user.setRolesSet(rols);
        userRepository.save(user);
    }
    @Override
    @Transactional
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Transactional
    public User getUserById(long id) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }

    @Override
    @Transactional
    public User findByUserName(String userName) {
        return userRepository.getUserByName(userName);
    }
}


