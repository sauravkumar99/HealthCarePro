package com.healthcarepro.repository;

import com.healthcarepro.model.Doctor;
import com.healthcarepro.model.TimeSlot;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TimeSlotRepository extends CrudRepository<TimeSlot, Long> {
	
	List<TimeSlot> findByDoctorAndStartTimeAfter(Doctor doctor, LocalDateTime currentTime);
	boolean existsByDoctorIdAndStartTimeLessThanEqualAndEndTimeGreaterThan(Long doctorId, LocalDateTime endTime, LocalDateTime startTime);
	
	List<TimeSlot> findByDoctorIdAndIsAvailableTrueAndStartTimeBetweenOrderByStartTime(Long doctorId, LocalDateTime startTime, LocalDateTime endTime);
	
}
