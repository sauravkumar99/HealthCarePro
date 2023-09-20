package com.healthcarepro.service.impl;

import com.healthcarepro.model.Doctor;
import com.healthcarepro.model.TimeSlot;
import com.healthcarepro.repository.DoctorRepository;
import com.healthcarepro.repository.TimeSlotRepository;
import com.healthcarepro.service.TimeSlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {
	
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	
	@Autowired
	private DoctorRepository doctorRepository;
	
	@Override
	public TimeSlot findNearestAvailableTimeSlot(Long doctorId, LocalDate appointmentDate) {
		LocalDateTime startOfDay = appointmentDate.atStartOfDay();
		LocalDateTime endOfDay = appointmentDate.atTime(23, 59, 59);
		
		List<TimeSlot> availableTimeSlots = timeSlotRepository.findByDoctorIdAndIsAvailableTrueAndStartTimeBetweenOrderByStartTime(doctorId, startOfDay, endOfDay);
		
		if (availableTimeSlots.isEmpty()) {
			throw new IllegalArgumentException("No timeSlot found for this doctor on the given date!");
		}
		return availableTimeSlots.get(0);
	}
	
	@Override
	public void addTimeSlot(Long id, LocalDateTime startTime, LocalDateTime endTime) {
		if(startTime.isAfter(endTime)) return;
		Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
		Doctor doctorDB;
		if(optionalDoctor.isPresent()) {
			doctorDB = optionalDoctor.get();
			LocalDateTime currentStartTime = startTime;
			List<TimeSlot> timeSlots = new ArrayList<>();
			while (currentStartTime.isBefore(endTime)){
				TimeSlot timeSlot = new TimeSlot();
				timeSlot.setStartTime(currentStartTime);
				currentStartTime = currentStartTime.plusMinutes(15);
				timeSlot.setEndTime(currentStartTime);
				timeSlot.setDoctor(doctorDB);
				timeSlots.add(timeSlot);
			}
			saveTimeSlot(timeSlots);
		}else{
			return;
		}
	}
	
	@Transactional
	public List<TimeSlot> saveTimeSlot(List<TimeSlot> timeSlots) {
		List<TimeSlot> timeSlotsList = new ArrayList<>();
		for (TimeSlot timeSlot: timeSlots) {
			// Check for overlapping time slots
			if (timeSlotRepository.existsByDoctorIdAndStartTimeLessThanEqualAndEndTimeGreaterThan(
					timeSlot.getDoctor().getId(), timeSlot.getEndTime(), timeSlot.getStartTime())) {
				throw new IllegalArgumentException("Overlapping time slot found!");
			}
			timeSlotsList.add(timeSlot);
		}
		timeSlotRepository.saveAll(timeSlotsList);
		return timeSlotsList;
	}
}
