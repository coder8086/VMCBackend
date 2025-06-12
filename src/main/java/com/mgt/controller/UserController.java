
package com.mgt.controller;

import com.mgt.jwtServices.JwtService;
import com.mgt.model.AuthRequest;
import com.mgt.model.User;
import com.mgt.model.UserInfoService;
import com.mgt.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;




@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4300"})
public class UserController
{

    @Autowired
    private UserInfoService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome this endpoint is not secure";
    }

    @PostMapping("/register")
    public ResponseEntity<?> addNewUser(@RequestBody User userInfo) {
        String result = service.addUser(userInfo);

        if (result.equals("Error: Username already exists!")) {

            return ResponseEntity.ok(Collections.singletonMap("message", "Duplicate entory"));
        }
        return ResponseEntity.ok(Collections.singletonMap("message", "User created successfully"));
    }


    @PostMapping("/login")
    public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

            if (authentication.isAuthenticated()) {
            


                        String token = jwtService.generateToken(authRequest.getUsername());
                        return ResponseEntity.ok(token);

                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
                }
          
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed: " + ex.getMessage());
        }
    }

    @GetMapping("/getUserById")
    public ResponseEntity<?> getUserById(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Step 1: Validate JWT token presence
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("error", "Missing or invalid Authorization header"));
            }

            // Step 2: Extract token and userId
            String token = authorizationHeader.substring(7);
            Long userId = jwtService.extractUserId(token);  // You should implement this method
            if (userId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("error", "Invalid JWT token"));
            }

            // Step 3: Retrieve user from DB
            Optional<User> optionalUser = userRepo.findById(userId);
            if (optionalUser.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Collections.singletonMap("error", "User not found"));
            }

            User user = optionalUser.get();

            // Step 4: Prepare response with role
            Map<String, Object> response = new HashMap<>();
            response.put("id", user.getId());
            response.put("firstName", user.getFirstName());
            response.put("lastName", user.getLastName());
            response.put("email", user.getUsername());
            response.put("role", user.getRole());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonMap("error", "An error occurred: " + e.getMessage()));
        }
    }



    @GetMapping("/testApi")
    public String getMethodName() {
        return "This secure api endpoint";
    }
    


}
