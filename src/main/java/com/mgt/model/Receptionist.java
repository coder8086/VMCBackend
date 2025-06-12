package com.mgt.model;

import jakarta.persistence.*;

@Entity
@Table(name="receptionist")
public class Receptionist
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Personal Information
    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="emailAddress")
    private String emailAddress;

    @Column(name="gender")
    private String gender;

    @Column(name="mobileNumber")
    private String mobileNumber;

    @Column(name="emeNumber")
    private String emergencyContactNumber;

    // Work Details
    @Column(name="deskId")
    private String deskId;

    @Column(name="qualification")
    private String qualification;

    @Column(name="experience")
    private String experience;

    // Account Information
    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Transient
    @Column(name="confirmPassword")
    private String confirmPassword;

    @Column(name="profilePicture")
    private String profilePicture; // file path or URL


    public Receptionist() {
    }

    public Receptionist(Long id, String firstName, String lastName, String emailAddress, String gender, String mobileNumber, String emergencyContactNumber, String deskId, String qualification, String experience, String username, String password, String confirmPassword, String profilePicture) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.gender = gender;
        this.mobileNumber = mobileNumber;
        this.emergencyContactNumber = emergencyContactNumber;
        this.deskId = deskId;
        this.qualification = qualification;
        this.experience = experience;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.profilePicture = profilePicture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmergencyContactNumber() {
        return emergencyContactNumber;
    }

    public void setEmergencyContactNumber(String emergencyContactNumber) {
        this.emergencyContactNumber = emergencyContactNumber;
    }

    public String getDeskId() {
        return deskId;
    }

    public void setDeskId(String deskId) {
        this.deskId = deskId;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
