package com.cvds.parcial.back_parcial_3.controller;

import com.cvds.parcial.back_parcial_3.dto.AppointmentDTO;
import com.cvds.parcial.back_parcial_3.model.Appointment;
import com.cvds.parcial.back_parcial_3.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping
    public Appointment createAppointment(@RequestBody AppointmentDTO dto) {
        return appointmentService.createAppointment(dto);
    }

    @GetMapping("/{email}")
    public List<Appointment> getAppointmentsByEmail(@PathVariable String email) {
        return appointmentService.getAppointmentsByEmail(email);
    }

    @GetMapping("/{email}/{status}")
    public List<Appointment> getAppointmentsByEmailAndStatus(
            @PathVariable String email,
            @PathVariable String status) {
        return appointmentService.getAppointmentsByEmailAndStatus(email, status);
    }

    @PutMapping("/cancel/{id}")
    public boolean cancelAppointment(@PathVariable String id) {
        return appointmentService.cancelAppointment(id);
    }
}
