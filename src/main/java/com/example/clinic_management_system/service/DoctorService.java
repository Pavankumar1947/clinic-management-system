package com.example.clinic_management_system.service;

import com.example.clinicmanagement.model.Appointment;
import com.example.clinicmanagement.model.Doctor;
import com.example.clinicmanagement.repository.AppointmentRepository;
import com.example.clinicmanagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // ✔ 1. Get available time slots
    public List<LocalTime> getAvailableTimeSlots(Long doctorId, LocalDate date) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        List<Appointment> bookedAppointments = appointmentRepository
                .findByDoctorIdAndDate(doctorId, date);

        List<LocalTime> bookedTimes = bookedAppointments.stream()
                .map(a -> a.getAppointmentTime().toLocalTime())
                .collect(Collectors.toList());

        return doctor.getAvailableTimes().stream()
                .filter(time -> !bookedTimes.contains(time))
                .collect(Collectors.toList());
    }

    // ✔ 2. Validate login
    public ResponseEntity<?> validateDoctorLogin(String email, String password) {
        Optional<Doctor> doctorOpt = doctorRepository.findByEmail(email);

        if (doctorOpt.isEmpty() || !passwordEncoder.matches(password, doctorOpt.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = tokenService.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token, "role", "DOCTOR"));
    }
}
