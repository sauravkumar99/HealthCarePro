package com.healthcarepro.service;

import com.healthcarepro.model.Doctor;

import java.time.LocalDateTime;
import java.util.List;

public interface DoctorService {
	
	List<Doctor> getAllDoctors();
	
	Doctor getDoctorById(Long id);
	
	Doctor getDoctorByName(String name);
	
	Doctor addDoctor(Doctor doctor);
	
	Doctor updateDoctor(Long id, Doctor doctor);
	
	void deleteDoctor(Long id);
	
	List<Doctor> getAllDoctorsBySpecialization(String specialization);
	
	List<Doctor> getAllDoctorsBySpecializationAndCity(String specialization, String city);
}
