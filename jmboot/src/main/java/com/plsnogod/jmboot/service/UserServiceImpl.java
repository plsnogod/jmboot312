package com.plsnogod.jmboot.service;

import com.plsnogod.jmboot.model.Role;
import com.plsnogod.jmboot.model.User;
import com.plsnogod.jmboot.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;


import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean addUser(User user) {
        User user1 = userRepository.getUserByEmail(user.getEmail());
        if (user1 != null) {
            return false;
        }
        user.setPassword(NoOpPasswordEncoder.getInstance().encode(user.getPassword()));
        userRepository.save(user);

        return true;
    }

    @Override
    public List<User> showAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User getUserById(long id) {
        User user = null;
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            user = optionalUser.get();
        }
        return user;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUserByEmail(email);
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : user.getSetRoles()
        ) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getNameRoles()));

        }

        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }

    public User findByUserByEmail(String email) {
        return userRepository.getUserByEmail(email);
    }
}

