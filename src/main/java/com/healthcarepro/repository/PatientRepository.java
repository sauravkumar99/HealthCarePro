package com.healthcarepro.repository;

import com.healthcarepro.model.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient, Long> {
	
	Patient findByName(String name);
}