package com.healthcarepro.service;

import com.healthcarepro.model.Patient;

import java.util.List;

public interface PatientService {
	List<Patient> getAllPatients();
	
	Patient getPatientByName(String name);
	
	Patient addPatient(Patient patient);
	
	Patient updatePatient(Long id, Patient patient);
	
	void deletePatient(Long id);
	
	Patient getPatientById(Long id);
}
