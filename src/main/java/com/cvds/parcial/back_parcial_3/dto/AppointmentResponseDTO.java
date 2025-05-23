package com.cvds.parcial.back_parcial_3.dto;

import java.time.LocalDate;

public class AppointmentResponseDTO {

    private String id;
    private String fullName;
    private String email;
    private String idNumber;
    private LocalDate appointmentDate;
    private String specialty;
    private String doctor;
    private String location;
    private String status;

    public AppointmentResponseDTO() {}

    public AppointmentResponseDTO(String id, String fullName, String email, String idNumber,
                                  LocalDate appointmentDate, String specialty,
                                  String doctor, String location, String status) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.idNumber = idNumber;
        this.appointmentDate = appointmentDate;
        this.specialty = specialty;
        this.doctor = doctor;
        this.location = location;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
