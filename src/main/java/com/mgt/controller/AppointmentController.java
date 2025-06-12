package com.mgt.controller;

import com.mgt.model.BookAppointment;
import com.mgt.repository.BookApptRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4300"})
public class AppointmentController {

    @Autowired
    private BookApptRepo bookApptRepo;

    @PostMapping("/addAppointment")
    public ResponseEntity<?> addAppointment(@RequestBody BookAppointment bookAppointment){
        bookApptRepo.save(bookAppointment);
        return ResponseEntity.ok(Collections.singletonMap("message" , "Appointment Booked"));
    }

    @GetMapping("/getAppointments")
    public List<BookAppointment> showAppt(){
        return bookApptRepo.findAll();

    }
}
