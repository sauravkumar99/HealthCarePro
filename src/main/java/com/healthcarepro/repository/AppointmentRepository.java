package com.healthcarepro.repository;

import com.healthcarepro.model.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
	
	boolean existsByDoctorIdAndPatientIdAndAppointmentDateTimeBetween(
			Long doctorId, Long patientId, LocalDateTime startOfDay, LocalDateTime endOfDay);
}
