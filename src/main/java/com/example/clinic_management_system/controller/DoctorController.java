package com.example.clinic_management_system.controller;

import com.example.clinicmanagement.service.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/availability")
    public ResponseEntity<?> getDoctorAvailability(
            @RequestParam Long doctorId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            HttpServletRequest request) {

        // Token validation logic (could be in filter/security context too)
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Invalid token");
        }

        try {
            List<String> availableTimes = doctorService.getAvailableTimeSlots(doctorId, date);
            return ResponseEntity.ok().body(availableTimes);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error");
        }
    }

    // Other endpoints (add doctor, list, etc.)
}
