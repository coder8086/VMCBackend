package com.mgt.controller;

import com.mgt.jwtServices.JwtService;
import com.mgt.model.Patient;
import com.mgt.model.User;
import com.mgt.repository.PatientRepo;
import com.mgt.repository.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4300"})
public class PatientController {

    @Autowired
    private PatientRepo patientRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/testPatient")
    public String getMethodName() {
        return "This patient api";
    }

   @PostMapping("/addPatient")
@PreAuthorize("hasRole('PATIENT')")
public ResponseEntity<?> addPatient(
        @RequestParam String firstName,
        @RequestParam String lastName,
        @RequestParam String phone,
        @RequestParam String dob, // as String, parse later
        @RequestParam String gender,
        @RequestParam String bloodGroup,
        @RequestParam String cityState,
        @RequestParam String emergencyContact,
        @RequestParam String address,
        @RequestHeader("Authorization") String authorizationHeader) {

    try {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Missing or invalid Authorization header"));
        }

        String token = authorizationHeader.substring(7);
        Long userId = jwtService.extractUserId(token);

        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Invalid JWT token"));
        }

        Optional<User> optionalUser = userRepo.findById(userId);
        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "User not found"));
        }

        // Convert dob string to LocalDate
        LocalDate dobParsed = LocalDate.parse(dob.trim());


        // Build patient object
        Patient patient = new Patient();
        patient.setFirstName(firstName);
        patient.setLastName(lastName);
        patient.setPhone(phone);
        patient.setDob(dobParsed);
        patient.setGender(gender);
        patient.setBloodGroup(bloodGroup);
        patient.setCityState(cityState);
        patient.setEmergencyContact(emergencyContact);
        patient.setAddress(address);
        patient.setUser(optionalUser.get());

        // Save patient
        patientRepo.save(patient);

        return ResponseEntity.ok(Collections.singletonMap("message", "Saved successfully!"));

    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Collections.singletonMap("error", "An error occurred: " + e.getMessage()));
    }
}

    @GetMapping("/getListPatient")
    @PreAuthorize("hasRole('RECEPTIONIST')")
    public List<Patient> getAllPatient() {
        return patientRepo.findAll();
    }

}
