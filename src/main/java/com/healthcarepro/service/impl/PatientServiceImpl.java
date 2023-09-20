package com.healthcarepro.service.impl;

import com.healthcarepro.model.Patient;
import com.healthcarepro.repository.PatientRepository;
import com.healthcarepro.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientRepository patientRepository;
	@Override
	public List<Patient> getAllPatients() {
		List<Patient> patients = new ArrayList<>();
		patientRepository.findAll().forEach(patients::add);
		return patients;
	}
	
	@Override
	public Patient getPatientByName(String name) {
		return patientRepository.findByName(name);
	}
	
	@Override
	public Patient addPatient(Patient patient) {
		return patientRepository.save(patient);
	}
	
	@Override
	public Patient updatePatient(Long id, Patient patient) {
		Optional<Patient> optionalPatient = patientRepository.findById(id);
		Patient patientDB = null;
		if(optionalPatient.isPresent()){
			patientDB = optionalPatient.get();
			patientDB.setName(patient.getName());
			patientDB.setAddress(patient.getAddress());
			patientDB.setCity(patient.getCity());
			patientDB.setState(patient.getState());
			patientDB.setCountry(patient.getCountry());
			patientDB.setWeight(patient.getWeight());
			patientDB.setPinCode(patient.getPinCode());
			patientRepository.save(patientDB);
		}
		return patientDB;
	}
	
	
	@Override
	public void deletePatient(Long id) {
		patientRepository.deleteById(id);
	}
	
	@Override
	public Patient getPatientById(Long id) {
		Optional<Patient> optionalPatient = patientRepository.findById(id);
		return optionalPatient.orElse(null);
	}
}
