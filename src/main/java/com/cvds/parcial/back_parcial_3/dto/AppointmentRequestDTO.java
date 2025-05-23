package com.cvds.parcial.back_parcial_3.dto;

import java.time.LocalDate;

public class AppointmentRequestDTO {

    private String fullName;
    private String email;
    private String idNumber;
    private LocalDate appointmentDate;
    private String specialty;

    public AppointmentRequestDTO() {}

    public AppointmentRequestDTO(String fullName, String email, String idNumber, LocalDate appointmentDate, String specialty) {
        this.fullName = fullName;
        this.email = email;
        this.idNumber = idNumber;
        this.appointmentDate = appointmentDate;
        this.specialty = specialty;
    }

    // Getters y Setters...

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
}
