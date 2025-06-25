package com.mgt.controller;

import com.mgt.jwtServices.JwtService;
import com.mgt.model.Doctor;
import com.mgt.model.User;
import com.mgt.repository.DoctorRepo;
import com.mgt.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"})
public class DoctorController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DoctorRepo doctorRepo;

    // CREATE DOCTOR PROFILE
    @PostMapping("/createProfile")
    public ResponseEntity<?> createDoctor(
            @RequestBody Doctor doctor,
            @RequestHeader(value = "Authorization", required = false) String authHeader) {

        System.out.println("Received Authorization Header: " + authHeader);

        try {
            // Validate JWT Bearer token
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("message", "Missing or invalid Authorization header"));
            }

            String token = authHeader.substring(7);
            Long userId = jwtService.extractUserId(token); // Method should return userId from token

            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("message", "Invalid JWT token"));
            }

            // Get authenticated user
            User user = userRepo.findById(userId)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            // Set profile as created
            user.setProfileCreated(true);
            userRepo.save(user);

            // Associate doctor with user and save
            doctor.setUser(user);
            doctorRepo.save(doctor);

            return ResponseEntity.ok(Collections.singletonMap("message", "Profile created successfully"));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("message", "Error saving profile: " + e.getMessage()));
        }
    }
}
