package com.example.clinic_management_system.repository;

import com.example.clinicmanagement.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    // ✔ 1. Find by email
    Optional<Patient> findByEmail(String email);

    // ✔ 2. Find by email or phone number
    Optional<Patient> findByEmailOrPhoneNumber(String email, String phoneNumber);
}