package com.example.alkananda.service;

import com.example.alkananda.entity.User;

import com.example.alkananda.repository.userRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final userRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
    public UserService(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        String encodedPassword=passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

}