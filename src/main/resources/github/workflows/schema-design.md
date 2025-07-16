# 🗂️ Clinic Management System – MySQL Schema Design

## 1. Doctor Table

```sql
CREATE TABLE Doctor (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  email VARCHAR(100) UNIQUE NOT NULL,
  phone_number VARCHAR(15),
  speciality VARCHAR(100),
  available_times JSON
);


CREATE TABLE Patient (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(100),
  email VARCHAR(100) UNIQUE NOT NULL,
  phone_number VARCHAR(15),
  date_of_birth DATE,
  gender VARCHAR(10)
);


CREATE TABLE Appointment (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  doctor_id BIGINT,
  patient_id BIGINT,
  appointment_time DATETIME,
  status VARCHAR(20),
  FOREIGN KEY (doctor_id) REFERENCES Doctor(id),
  FOREIGN KEY (patient_id) REFERENCES Patient(id)
);


CREATE TABLE Prescription (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  appointment_id BIGINT,
  doctor_id BIGINT,
  patient_id BIGINT,
  description TEXT,
  date_issued DATE,
  FOREIGN KEY (appointment_id) REFERENCES Appointment(id),
  FOREIGN KEY (doctor_id) REFERENCES Doctor(id),
  FOREIGN KEY (patient_id) REFERENCES Patient(id)
);
