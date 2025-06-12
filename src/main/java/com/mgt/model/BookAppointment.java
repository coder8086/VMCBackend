package com.mgt.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="appointments")
public class BookAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(name="patientName")
    private String patientName;

    @Column(name="contactNumber")
    private String contactNumber;

    @Column(name="age")
    private int age;

    @Column(name="gender")
    private String gender;

    @Column(name="disease")
    private String disease;

    @Column(name="selectedDate")
    private LocalDate selectedDate;

    @Column(name="selectedTime")
    private LocalTime selectedTime;

    @Column(name="message")
    private String message;

    public BookAppointment() {
    }

    public BookAppointment(Long id, String patientName, String contactNumber, int age, String gender, String disease, LocalDate selectedDate, LocalTime selectedTime, String message) {
        this.id = id;
        this.patientName = patientName;
        this.contactNumber = contactNumber;
        this.age = age;
        this.gender = gender;
        this.disease = disease;
        this.selectedDate = selectedDate;
        this.selectedTime = selectedTime;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public LocalDate getSelectedDate() {
        return selectedDate;
    }

    public void setSelectedDate(LocalDate selectedDate) {
        this.selectedDate = selectedDate;
    }

    public LocalTime getSelectedTime() {
        return selectedTime;
    }

    public void setSelectedTime(LocalTime selectedTime) {
        this.selectedTime = selectedTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
