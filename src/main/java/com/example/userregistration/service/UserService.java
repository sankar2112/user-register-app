package com.example.userregistration.service;

import com.example.userregistration.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.userregistration.model.User;
import com.example.userregistration.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public User registerUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Email is already registered!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean validateUser(String email, String password) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isEmpty()) {
            return false;
        }
        return passwordEncoder.matches(password, existingUser.get().getPassword());
    }

    public String validateAndGenerateToken(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword())) {
            return "token-" + email; // Simple token generation logic for demo purposes
        }
        throw new IllegalArgumentException("Invalid email or password");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}


