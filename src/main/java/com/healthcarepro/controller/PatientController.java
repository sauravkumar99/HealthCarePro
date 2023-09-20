package com.healthcarepro.controller;

import com.healthcarepro.model.Patient;
import com.healthcarepro.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {
	
	@Autowired
	private PatientService patientService;
	
	@GetMapping("/patients")
	public List<Patient> getAllPatients() {
		return patientService.getAllPatients();
	}
	
	@GetMapping("/patient/{id}")
	public Patient getPatientById(@PathVariable Long id) {
		return patientService.getPatientById(id);
	}
	
	
    @PostMapping("/patient")
    public Patient addPatient(@Validated @RequestBody Patient patient) {
        return patientService.addPatient(patient);
    }
	
    @PutMapping("/patient/{id}")
    public Patient updatePatient(@Validated @RequestBody Patient patient, @PathVariable Long id) {
       return patientService.updatePatient(id , patient);
    }

    @DeleteMapping("/patient/{id}")
    public void deletePatient(@PathVariable Long id) {
	    patientService.deletePatient(id);
    }
}