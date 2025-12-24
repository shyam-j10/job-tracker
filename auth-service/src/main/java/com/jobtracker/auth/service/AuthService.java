package com.jobtracker.auth.service;

import com.jobtracker.auth.dto.LoginRequest;
import com.jobtracker.auth.dto.LoginResponse;
import com.jobtracker.auth.dto.RegisterRequest;
import com.jobtracker.auth.entity.UserEntity;
import com.jobtracker.auth.entity.UserProfileEntity;
import com.jobtracker.auth.exception.DuplicateEmailException;
import com.jobtracker.auth.exception.InvalidCredentialsException;
import com.jobtracker.auth.repository.UserProfileRepository;
import com.jobtracker.auth.repository.UserRepository;
import com.jobtracker.auth.security.JwtUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthService.class);

    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       UserProfileRepository userProfileRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Transactional
    public void register(RegisterRequest request) {

        log.info("Register attempt for email: {}", request.getEmail());

        if (userRepository.existsByEmail(request.getEmail())) {
            log.warn("Registration failed - email already exists: {}", request.getEmail());
            throw new DuplicateEmailException("Email already registered");
        }

        // Create User
        UserEntity user = new UserEntity();
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));

        UserEntity savedUser = userRepository.save(user);

        // Create Profile
        UserProfileEntity profile = new UserProfileEntity();
        profile.setUserId(savedUser.getId());

        userProfileRepository.save(profile);

        log.info("User registered successfully with id: {}", savedUser.getId());
        
    }

    public LoginResponse login(LoginRequest request) {

        log.info("Login attempt for email: {}", request.getEmail());

        UserEntity user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            log.warn("Login failed for email: {}", request.getEmail());
            throw new InvalidCredentialsException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user);

        log.info("Login successful for userId: {}", user.getId());

        return new LoginResponse(token, 3600);
    }

}
