package com.healthcarepro.service;

import com.healthcarepro.model.Appointment;

import java.time.LocalDate;

public interface AppointmentService {
	Appointment scheduleAppointment(Long doctorId, Long patientId, LocalDate appointmentDate, String appointmentMode);
}
