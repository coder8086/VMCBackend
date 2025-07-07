package com.mgt.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.mgt.model.videoCalling;
import com.mgt.repository.VideoCallingRepo;

import io.jsonwebtoken.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = { "http://localhost:4200" })
public class videoCallController {

    private final List<SseEmitter> emitters = new CopyOnWriteArrayList<>();

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
            sendUpdatedListToClients();
            return ResponseEntity.ok(Collections.singletonMap("message", "link created"));
        }
    }

    @GetMapping("/getAllVideoCallings")
    public ResponseEntity<List<videoCalling>> getAllVideoCallings() {
        List<videoCalling> allVideoCallings = videoCallingRepo.findAll();
        return ResponseEntity.ok(allVideoCallings);
    }

 @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
public SseEmitter streamUpdates() {
    SseEmitter emitter = new SseEmitter();
    // Store and use this emitter to send updates from elsewhere in the app
    emitters.add(emitter);

    emitter.onCompletion(() -> emitters.remove(emitter));
    emitter.onTimeout(() -> emitters.remove(emitter));
    emitter.onError((e) -> emitters.remove(emitter));

    return emitter;
}


    @DeleteMapping("/deleteVideoCalling/{doctorId}")
    public ResponseEntity<?> deleteVideoCalling(@PathVariable Long doctorId) {
        videoCalling video = videoCallingRepo.findByDoctorId(doctorId);

        if (video != null) {
            videoCallingRepo.delete(video);
            sendUpdatedListToClients();
            // Notify all clients about the deletion
            return ResponseEntity.ok(Collections.singletonMap("message", "Video calling entry deleted successfully"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Video calling entry not found"));
        }
    }

     private void sendUpdatedListToClients() {
        List<videoCalling> itemList = videoCallingRepo.findAll();

        List<SseEmitter> deadEmitters = new ArrayList<>();
        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(SseEmitter.event()
                        .name("update")
                        .data(itemList)
                        .reconnectTime(3000));
            } catch (IOException | java.io.IOException e) {
                deadEmitters.add(emitter);
            }
        }
        emitters.removeAll(deadEmitters);
    }

}
