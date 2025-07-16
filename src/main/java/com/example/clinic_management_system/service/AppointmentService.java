package com.example.clinic_management_system.service;

import com.example.clinicmanagement.model.Appointment;
import com.example.clinicmanagement.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // 1. Book Appointment
    public Appointment bookAppointment(Appointment appointment) {
        // Optional: check if time is already booked
        return appointmentRepository.save(appointment);
    }

    // 2. Get Appointments by Doctor and Date
    public List<Appointment> getAppointmentsByDoctorAndDate(Long doctorId, LocalDate date) {
        return appointmentRepository.findByDoctorIdAndDate(doctorId, date);
    }
}
