package com.mgt.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mgt.model.videoCalling;
import com.mgt.repository.VideoCallingRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class videoCallController {

  

    @Autowired
    private VideoCallingRepo videoCallingRepo;

    @GetMapping("/testVideoCalling")
    public String getMethodName() {
        return "video calling tested";
    }

    @PostMapping("/setVideoLink")
    public ResponseEntity<?> genrateLink(@RequestBody videoCalling vCalling) {
        // Check if the video link already exists
        videoCalling existingVideo = videoCallingRepo.findByDoctorId(vCalling.getDoctorId());
        if (existingVideo != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("error", "Video calling link already exists for this doctor"));
        } else {
            videoCallingRepo.save(vCalling);
            // Notify all clients about the new video calling link
        
            return ResponseEntity.ok(Collections.singletonMap("message", "link created"));
        }
    }

    @GetMapping("/getAllVideoCallings")
    public ResponseEntity<List<videoCalling>> getAllVideoCallings() {
        List<videoCalling> allVideoCallings = videoCallingRepo.findAll();
        return ResponseEntity.ok(allVideoCallings);
    }

    @GetMapping("/getDoctorByLink/{videoLink}")
public ResponseEntity<?> getDoctorByLink(@PathVariable String videoLink) {
    videoCalling video = videoCallingRepo.findByVideoLink(videoLink);
    if (video != null) {
        return ResponseEntity.ok(video);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap("error", "Doctor not found for the given video link"));
    }
}


    @DeleteMapping("/deleteVideoCalling/{doctorId}")
    public ResponseEntity<?> deleteVideoCalling(@PathVariable Long doctorId) {
        videoCalling video = videoCallingRepo.findByDoctorId(doctorId);

        if (video != null) {
            videoCallingRepo.delete(video);
         
            // Notify all clients about the deletion
            return ResponseEntity.ok(Collections.singletonMap("message", "Video calling entry deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Video calling entry not found"));
        }
    }

   

}
