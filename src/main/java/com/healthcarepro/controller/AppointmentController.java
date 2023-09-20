package com.healthcarepro.controller;

import com.healthcarepro.model.Appointment;
import com.healthcarepro.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@PostMapping("/appointment")
	public Appointment scheduleAppointment(@RequestParam Long doctorId, @RequestParam Long patientId,
			@RequestParam(value = "date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate appointmentDate,
			@RequestParam(value = "type") String appointmentMode){
		return appointmentService.scheduleAppointment(doctorId, patientId, appointmentDate, appointmentMode);
	}
}
