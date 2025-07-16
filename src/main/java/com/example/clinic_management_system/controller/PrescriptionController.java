package com.example.clinic_management_system.controller;

import com.example.clinicmanagement.model.Prescription;
import com.example.clinicmanagement.service.PrescriptionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping
    public ResponseEntity<?> savePrescription(
            @Valid @RequestBody Prescription prescription,
            HttpServletRequest request) {

        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("Missing or invalid token");
        }

        try {
            Prescription saved = prescriptionService.save(prescription);
            return ResponseEntity.ok().body(
                    java.util.Map.of("message", "Prescription saved successfully", "data", saved)
            );
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving prescription");
        }
    }
}
