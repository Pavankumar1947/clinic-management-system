package com.example.clinic_management_system.model;

import jakarta.persistence.*;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private String speciality;

	private String email;

	private String phoneNumber;

	@ElementCollection
	@CollectionTable(name = "doctor_available_times", joinColumns = @JoinColumn(name = "doctor_id"))
	@Column(name = "available_time")
	private List<LocalTime> availableTimes;

	// Constructors
	public Doctor() {
	}

	public Doctor(String name, String speciality, String email, String phoneNumber, List<LocalTime> availableTimes) {
		this.name = name;
		this.speciality = speciality;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.availableTimes = availableTimes;
	}

	// Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<LocalTime> getAvailableTimes() {
		return availableTimes;
	}

	public void setAvailableTimes(List<LocalTime> availableTimes) {
		this.availableTimes = availableTimes;
	}

}
