package com.cvds.parcial.back_parcial_3.service;

import com.cvds.parcial.back_parcial_3.dto.AppointmentDTO;
import com.cvds.parcial.back_parcial_3.model.Appointment;
import com.cvds.parcial.back_parcial_3.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment createAppointment(AppointmentDTO dto) {
        LocalDate today = LocalDate.now();

        if (dto.getAppointmentDate() == null || dto.getAppointmentDate().isBefore(today)) {
            return appointmentRepository.save(new Appointment(
                    null,
                    dto.getFullName(),
                    dto.getIdNumber(),
                    dto.getEmail(),
                    dto.getSpecialty(),
                    dto.getDoctor(),
                    dto.getLocation(),
                    dto.getAppointmentDate(),
                    "Rechazada"
            ));
        }

        Appointment appointment = new Appointment(
                null,
                dto.getFullName(),
                dto.getIdNumber(),
                dto.getEmail(),
                dto.getSpecialty(),
                dto.getDoctor(),
                dto.getLocation(),
                dto.getAppointmentDate(),
                "Confirmada"
        );

        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointmentsByEmail(String email) {
        return appointmentRepository.findAll()
                .stream()
                .filter(app -> app.getEmail().equalsIgnoreCase(email))
                .collect(Collectors.toList());
    }

    public List<Appointment> getAppointmentsByEmailAndStatus(String email, String status) {
        return appointmentRepository.findAll()
                .stream()
                .filter(app -> app.getEmail().equalsIgnoreCase(email)
                        && app.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    public boolean cancelAppointment(String appointmentId) {
        Optional<Appointment> optional = appointmentRepository.findById(appointmentId);
        if (optional.isPresent()) {
            Appointment app = optional.get();
            app.setStatus("Cancelada");
            appointmentRepository.save(app);
            return true;
        }
        return false;
    }
}
