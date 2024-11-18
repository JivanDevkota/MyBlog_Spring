package com.nsu.thmeleafproject.service;

import com.nsu.thmeleafproject.model.User;
import com.nsu.thmeleafproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerUser(User user) {
        user.setRole("ROLE_USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
      return userRepository.save(user);
    }

    public User loginUser(String username,String password) {
        User user = userRepository.findByUsernameAndPassword(username,password);
        if (user == null) {
            return null;
        }
        return user;
    }



}
