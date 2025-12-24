package com.jobtracker.auth.controller;

import com.jobtracker.auth.dto.ProfileResponse;
import com.jobtracker.auth.dto.UpdateProfileRequest;
import com.jobtracker.auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final AuthService authService;

    public ProfileController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> getProfile(
            @RequestHeader("X-User-Id") Long userId
            ) {

        return ResponseEntity.ok(authService.getProfile(userId));
    }

    @PutMapping("/me")
    public ResponseEntity<String> updateProfile(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody UpdateProfileRequest request) {

        authService.updateProfile(userId, request);
        return ResponseEntity.ok("Profile updated successfully");
    }
}
