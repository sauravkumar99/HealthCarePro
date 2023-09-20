package com.healthcarepro.service.impl;

import com.healthcarepro.model.*;
import com.healthcarepro.repository.AppointmentRepository;
import com.healthcarepro.repository.DoctorRepository;
import com.healthcarepro.repository.PatientRepository;
import com.healthcarepro.repository.TimeSlotRepository;
import com.healthcarepro.service.AppointmentService;
import com.healthcarepro.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private AppointmentRepository appointmentRepository;
	
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	
	@Autowired
	TimeSlotService timeSlotService;
	
	@Override
	public Appointment scheduleAppointment(Long doctorId, Long patientId, LocalDate appointmentDate, String appointmentMode) {
		LocalDate todayDate = LocalDate.now();
		if(appointmentDate.isBefore(todayDate)) {
			System.out.println("Invalid Date : " + appointmentDate);
			return null;
		}
		Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorId);
		if(!optionalDoctor.isPresent()) {
			System.out.println("Invalid doctorId : " + doctorId);
			return null;
		}
		Optional<Patient> optionalPatient = patientRepository.findById(patientId);
		if(!optionalPatient.isPresent()) {
			System.out.println("Invalid PatientId : " + patientId);
			return null;
		}
		Doctor doctorDB = optionalDoctor.get();
		Patient patientDB = optionalPatient.get();
		
		LocalDateTime startOfDay = appointmentDate.atStartOfDay();
		LocalDateTime endOfDay = appointmentDate.atTime(23, 59, 59);
		
		if (appointmentRepository.existsByDoctorIdAndPatientIdAndAppointmentDateTimeBetween(doctorId, patientId, startOfDay, endOfDay)) {
			throw new IllegalArgumentException("Appointment already exists for this doctor and patient on the given date!");
		}
		
//		List<TimeSlot> byDoctorAndStartTimeAfter = timeSlotRepository.findByDoctorAndStartTimeAfter(doctorDB, LocalDateTime.now());
		TimeSlot nearestAvailableTimeSlot = timeSlotService.findNearestAvailableTimeSlot(doctorId, appointmentDate);
		nearestAvailableTimeSlot.setAvailable(false);
		timeSlotRepository.save(nearestAvailableTimeSlot);
		
		Appointment appointment = new Appointment();
		appointment.setDoctor(doctorDB);
		appointment.setPatient(patientDB);
		if(appointmentMode.equalsIgnoreCase("online")){
			appointment.setAppointmentMode(AppointmentMode.ONLINE);
		} else if(appointmentMode.equalsIgnoreCase("offline")){
			appointment.setAppointmentMode(AppointmentMode.OFFLINE);
		}
		appointment.setAppointmentDateTime(nearestAvailableTimeSlot.getStartTime());
		appointment.setStatus(AppointmentStatus.SCHEDULED);
		return appointmentRepository.save(appointment);
	}
}
