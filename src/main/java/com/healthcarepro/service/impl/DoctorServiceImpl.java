package com.healthcarepro.service.impl;

import com.healthcarepro.model.Doctor;
import com.healthcarepro.model.TimeSlot;
import com.healthcarepro.repository.DoctorRepository;
import com.healthcarepro.repository.TimeSlotRepository;
import com.healthcarepro.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	public List<Doctor> getAllDoctors(){
		List<Doctor> doctors = new ArrayList<>();
		doctorRepository.findAll().forEach(doctors::add);
		return doctors;
	}
	
	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public Doctor getDoctorById(Long id) {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
		return optionalDoctor.orElse(null);
	}
	
	public Doctor updateDoctor(Long id, Doctor doctor) {
		Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
		Doctor doctorDB = null;
		if(optionalDoctor.isPresent()){
			doctorDB = optionalDoctor.get();
			doctorDB.setName(doctor.getName());
			doctorDB.setAddress(doctor.getAddress());
			doctorDB.setCity(doctor.getCity());
			doctorDB.setState(doctor.getState());
			doctorDB.setCountry(doctor.getCountry());
			doctorDB.setPinCode(doctor.getPinCode());
			doctorDB.setDegree(doctor.getDegree());
			doctorDB.setExperience(doctor.getExperience());
			doctorDB.setSpecialization(doctor.getSpecialization());
			doctorRepository.save(doctorDB);
		}
		return doctorDB;
	}
	
	public void deleteDoctor(Long id) {
		doctorRepository.deleteById(id);
	}
	
	@Override
	public List<Doctor> getAllDoctorsBySpecialization(String specialization) {
		return doctorRepository.findAllBySpecialization(specialization);
	}
	
	@Override
	public List<Doctor> getAllDoctorsBySpecializationAndCity(String specialization, String city) {
		return doctorRepository.findAllBySpecializationAndCity(specialization,city);
	}
	
	public Doctor getDoctorByName(String name) {
		return doctorRepository.findByName(name);
	}
}
