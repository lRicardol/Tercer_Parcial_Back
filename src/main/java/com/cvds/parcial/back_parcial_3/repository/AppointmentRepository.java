package com.cvds.parcial.back_parcial_3.repository;

import com.cvds.parcial.back_parcial_3.model.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {

    List<Appointment> findByIdNumber(String idNumber);

    List<Appointment> findBySpecialtyAndAppointmentDate(String specialty, LocalDate appointmentDate);

}
