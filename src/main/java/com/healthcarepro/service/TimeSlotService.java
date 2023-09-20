package com.healthcarepro.service;

import com.healthcarepro.model.TimeSlot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TimeSlotService {
	
	public TimeSlot findNearestAvailableTimeSlot(Long doctorId, LocalDate appointmentDate);
	
	void addTimeSlot(Long id, LocalDateTime startTime, LocalDateTime endTime);
}
