package com.example.userregistration.controller;

import com.example.userregistration.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.userregistration.model.User;
import com.example.userregistration.service.UserService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            User registeredUser = userService.registerUser(user);
            return ResponseEntity.ok(registeredUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/validate")
    public ResponseEntity<?> validateUser(@RequestParam String email, @RequestParam String password) {
        boolean isValid = userService.validateUser(email, password);
        if (isValid) {
            return ResponseEntity.ok("User validated successfully.");
        } else {
            return ResponseEntity.status(401).body("Invalid email or password.");
        }
    }


//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
//        try {
//            String token = userService.validateAndGenerateToken(email, password);
//            return ResponseEntity.ok().body(token);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            String token = userService.validateAndGenerateToken(email, password);
            return ResponseEntity.ok().body("Bearer " + token);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }

//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("not implemented");

    }

//
//    @GetMapping("/getall")
//    public ResponseEntity<?> getAllUsers(@RequestHeader("Authorization") String authHeader) {
//        if (!authHeader.startsWith("Bearer ")) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token format");
//        }
//        String token = authHeader.substring(7);
//        String email = token.replace("token-", ""); // Extract email from token
//
//        // Simulate token validation by checking if the user exists
//        if (userRepository.findByEmail(email).isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
//        }
//
//        List<User> users = userService.getAllUsers();
//        return ResponseEntity.ok(users);
//    }


}
