package com.healthcarepro.controller;

import com.healthcarepro.model.Doctor;
import com.healthcarepro.service.DoctorService;
import com.healthcarepro.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class DoctorController {
	
	@Autowired
	private DoctorService doctorService;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
	@GetMapping("/doctors")
	public List<Doctor> getAllDoctors() {
		return doctorService.getAllDoctors();
	}
	
	@GetMapping("/doctor/{id}")
	public Doctor getDoctorById(@PathVariable Long id) {
		return doctorService.getDoctorById(id);
	}
	
	@GetMapping("/doctor/specialization/{specialization}")
	public List<Doctor> getAllDoctorsBySpecialization(@PathVariable String specialization) {
		return doctorService.getAllDoctorsBySpecialization(specialization);
	}
	
	@GetMapping("/doctor/specialization/{specialization}/city/{city}")
	public List<Doctor> getAllDoctorsBySpecializationAndCity(@PathVariable String specialization, @PathVariable String city) {
		return doctorService.getAllDoctorsBySpecializationAndCity(specialization, city);
	}
	
    @PostMapping("/doctor")
    public Doctor addDoctor(@Validated @RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }
	
    @PutMapping("/doctor/{id}")
    public Doctor updateDoctor(@Validated @RequestBody Doctor doctor, @PathVariable Long id) {
       return doctorService.updateDoctor(id , doctor);
    }

    @DeleteMapping("/doctor/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        doctorService.deleteDoctor(id);
    }
	
	@PostMapping("/doctor/{id}/timeslot")
	public void addTimeslot(@PathVariable Long id,
	                        @RequestParam(value = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
	                        @RequestParam(value = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)  LocalDateTime endTime) {
		timeSlotService.addTimeSlot(id, startTime, endTime);
	}
}