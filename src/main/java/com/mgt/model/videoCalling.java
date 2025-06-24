package com.mgt.model;

import jakarta.persistence.*;

@Entity
public class videoCalling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String doctorName;

    private String specialization;

    private int experience;

    private String videoLink;

    @ManyToOne
    @JoinColumn(name = "doctor_id")  // Foreign key in video_calling table
    private Doctor doctor;

    

  
    public videoCalling() {
    }

    

    public videoCalling(long id, String doctorName, String specialization, int experience, String videoLink,
            Doctor doctor) {
        this.id = id;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.experience = experience;
        this.videoLink = videoLink;
        this.doctor = doctor;
    }



    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }
}
