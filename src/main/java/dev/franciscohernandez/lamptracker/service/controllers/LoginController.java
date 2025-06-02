package dev.franciscohernandez.lamptracker.service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import dev.franciscohernandez.lamptracker.service.models.UserModel;
import dev.franciscohernandez.lamptracker.service.repository.UserRepository;


@RestController
@RequestMapping("/v1")
public class LoginController {

    @Autowired
    UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // DTO for login request
    public static class LoginRequest {
        public String username;
        public String password;
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        UserModel user = userRepository.findByUsername(loginRequest.username);
        if (user == null) {
            return "Invalid username or password";
        }

        if (!passwordEncoder.matches(loginRequest.password, user.getPassword())) {
            return "Invalid username or password";
        }
        // TODO: Generate and return a JWT or session token here for real applications
        return "Login successful";
    }

    // DTO for registration request
    public static class RegisterRequest {
        public String username;
        public String password;
        public String firstName;
        public String lastName;
        public String email;
        public String role;
        public String status;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        // Check if username already exists
        if (userRepository.findByUsername(registerRequest.username) != null) {
            return "Username already exists";
        }

        UserModel newUser = new UserModel();
        newUser.setUsername(registerRequest.username);
        newUser.setPassword(passwordEncoder.encode(registerRequest.password)); // Hash password
        newUser.setFirstName(registerRequest.firstName);
        newUser.setLastName(registerRequest.lastName);
        newUser.setEmail(registerRequest.email);
        newUser.setRole(registerRequest.role);
        newUser.setStatus(registerRequest.status);

        userRepository.save(newUser);
        return "Registration successful";
    }

    @PostMapping("/test")
    public String test(@RequestBody RegisterRequest user) {
        //TODO: process POST request
        
        return "Test successful: " + user.username + ", " + user.firstName + ", " + user.lastName;
    }
    

    
    

}
