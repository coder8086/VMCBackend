package com.mgt.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mgt.model.videoCalling;
import com.mgt.repository.VideoCallingRepo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api")
@CrossOrigin(origins = {"http://localhost:4200","http://localhost:4300"})
public class videoCallController {

    @Autowired
    private VideoCallingRepo videoCallingRepo;

    @GetMapping("/testVideoCalling")
    public String getMethodName() {
        return "video calling tested";
    }
    

    @PostMapping("/setVideoLink")
    public ResponseEntity<?> genrateLink(@RequestBody videoCalling vCalling){
        videoCallingRepo.save(vCalling);
        return ResponseEntity.ok(Collections.singletonMap("message", "link created"));
    }

    @GetMapping("/getVideoLink")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    


}
