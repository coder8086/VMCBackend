package com.mgt.model;

import jakarta.persistence.*;

@Entity
@Table(name = "videoCallContainer")
public class videoCalling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "doctorName")
    private String doctorName;

    @Column(name = "doctorId")
    private long doctorId; 

    @Column(name = "specialization")
    private String specialization;

    @Column(name = "experience")
    private int experience;

    @Column(name = "videoLink")
    private String videoLink;

    

  
    public videoCalling() {
    }

    

    public videoCalling(long id, String doctorName, long doctorId, String specialization, int experience, String videoLink) {
        this.id = id;
        this.doctorName = doctorName;
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.experience = experience;
        this.videoLink = videoLink;

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

    public long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(long doctorId) {
        this.doctorId = doctorId;
    }

    
}
