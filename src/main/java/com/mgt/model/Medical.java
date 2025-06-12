package com.mgt.model;

import jakarta.persistence.*;

@Entity
@Table(name="medical")
public class Medical
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Store & Owner Information
    @Column(name="medicalStoreName")
    private String medicalStoreName;

    @Column(name="firstName")
    private String firstName;

    @Column(name="lastName")
    private String lastName;

    @Column(name="gender")
    private String gender;

    @Column(name="emailAddress")
    private String emailAddress;

    @Column(name="phoneNumber")
    private String phoneNumber;

    @Column(name="alternateContactNumber")
    private String alternateContactNumber;

    // Optional and Regulatory Info
    @Column(name="medicalLicenseNumber")
    private String medicalLicenseNumber;

    @Column(name="gstNumber")
    private String gstNumber;

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

    public Medical() {
    }

    public Medical(Long id, String medicalStoreName, String firstName, String lastName, String gender, String emailAddress, String phoneNumber, String alternateContactNumber, String medicalLicenseNumber, String gstNumber, String username, String password, String confirmPassword, String profilePicture) {
        this.id = id;
        this.medicalStoreName = medicalStoreName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.alternateContactNumber = alternateContactNumber;
        this.medicalLicenseNumber = medicalLicenseNumber;
        this.gstNumber = gstNumber;
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

    public String getMedicalStoreName() {
        return medicalStoreName;
    }

    public void setMedicalStoreName(String medicalStoreName) {
        this.medicalStoreName = medicalStoreName;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    public void setAlternateContactNumber(String alternateContactNumber) {
        this.alternateContactNumber = alternateContactNumber;
    }

    public String getMedicalLicenseNumber() {
        return medicalLicenseNumber;
    }

    public void setMedicalLicenseNumber(String medicalLicenseNumber) {
        this.medicalLicenseNumber = medicalLicenseNumber;
    }

    public String getGstNumber() {
        return gstNumber;
    }

    public void setGstNumber(String gstNumber) {
        this.gstNumber = gstNumber;
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
