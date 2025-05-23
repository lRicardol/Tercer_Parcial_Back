package com.cvds.parcial.back_parcial_3.service;

import com.cvds.parcial.back_parcial_3.dto.AppointmentDTO;
import com.cvds.parcial.back_parcial_3.model.Appointment;
import com.cvds.parcial.back_parcial_3.model.Specialty;
import com.cvds.parcial.back_parcial_3.repository.AppointmentRepository;
import com.cvds.parcial.back_parcial_3.repository.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AppointmentServiceTest {

    private AppointmentRepository appointmentRepository;
    private SpecialtyRepository specialtyRepository;
    private AppointmentService appointmentService;

    @BeforeEach
    void setUp() {
        appointmentRepository = Mockito.mock(AppointmentRepository.class);
        specialtyRepository = Mockito.mock(SpecialtyRepository.class);
        appointmentService = new AppointmentService();
        appointmentService.appointmentRepository = appointmentRepository;
        appointmentService.specialtyRepository = specialtyRepository;
    }

    @Test
    void testCreateAppointmentConfirmed() {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setNombre("Juan");
        dto.setCedula("12345678");
        dto.setCorreo("juan@mail.com");
        dto.setFecha("30-05-2025");
        dto.setEspecialidad("medicina general");

        Specialty specialty = new Specialty("medicina general", "desc", "Dr. Juan", "Piso 1", "img.png");

        when(specialtyRepository.findByNombre("medicina general")).thenReturn(Optional.of(specialty));

        Appointment result = appointmentService.createAppointment(dto);

        assertEquals("Confirmada", result.getEstado());
        assertEquals("juan@mail.com", result.getCorreo());
        verify(appointmentRepository).save(any(Appointment.class));
    }

    @Test
    void testCreateAppointmentRejectedDueToPastDate() {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setNombre("Ana");
        dto.setCedula("987654");
        dto.setCorreo("ana@mail.com");
        dto.setFecha("01-01-2020");
        dto.setEspecialidad("odontologia");

        Specialty specialty = new Specialty("odontologia", "desc", "Dr. Ana", "Piso 3", "img.png");

        when(specialtyRepository.findByNombre("odontologia")).thenReturn(Optional.of(specialty));

        Appointment result = appointmentService.createAppointment(dto);

        assertEquals("Rechazada", result.getEstado());
        verify(appointmentRepository).save(any(Appointment.class));
    }

    @Test
    void testCreateAppointmentRejectedDueToInvalidSpecialty() {
        AppointmentDTO dto = new AppointmentDTO();
        dto.setNombre("Pedro");
        dto.setCedula("111222");
        dto.setCorreo("pedro@mail.com");
        dto.setFecha("30-05-2025");
        dto.setEspecialidad("no existe");

        when(specialtyRepository.findByNombre("no existe")).thenReturn(Optional.empty());

        Appointment result = appointmentService.createAppointment(dto);

        assertEquals("Rechazada", result.getEstado());
        verify(appointmentRepository).save(any(Appointment.class));
    }

    @Test
    void testGetAppointmentsByEmail() {
        String email = "prueba@mail.com";
        Appointment cita1 = new Appointment();
        Appointment cita2 = new Appointment();

        when(appointmentRepository.findByCorreo(email)).thenReturn(List.of(cita1, cita2));

        List<Appointment> result = appointmentService.getAppointmentsByEmail(email);

        assertEquals(2, result.size());
    }

    @Test
    void testGetAppointmentsByEmailAndStatus() {
        String email = "prueba@mail.com";
        String estado = "Confirmada";
        Appointment cita = new Appointment();
        cita.setEstado("Confirmada");

        when(appointmentRepository.findByCorreoAndEstado(email, estado)).thenReturn(List.of(cita));

        List<Appointment> result = appointmentService.getAppointmentsByEmailAndStatus(email, estado);

        assertEquals(1, result.size());
        assertEquals("Confirmada", result.get(0).getEstado());
    }

    @Test
    void testCancelAppointmentSuccess() {
        String id = "abc123";
        Appointment cita = new Appointment();
        cita.setId(id);
        cita.setEstado("Confirmada");

        when(appointmentRepository.findById(id)).thenReturn(Optional.of(cita));

        boolean resultado = appointmentService.cancelAppointment(id);

        assertTrue(resultado);
        assertEquals("Cancelada", cita.getEstado());
        verify(appointmentRepository).save(cita);
    }

    @Test
    void testCancelAppointmentNotFound() {
        String id = "inexistente";

        when(appointmentRepository.findById(id)).thenReturn(Optional.empty());

        boolean resultado = appointmentService.cancelAppointment(id);

        assertFalse(resultado);
        verify(appointmentRepository, never()).save(any());
    }
}
