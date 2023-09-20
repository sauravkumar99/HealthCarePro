package com.healthcarepro.repository;

import com.healthcarepro.model.Doctor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DoctorRepository extends CrudRepository<Doctor, Long> {
	
	Doctor findByName(String name);
	List<Doctor> findAllBySpecialization(String specialization);
	List<Doctor> findAllBySpecializationAndCity(String specialization, String city);
}