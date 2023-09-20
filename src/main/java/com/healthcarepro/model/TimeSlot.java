package com.healthcarepro.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(uniqueConstraints = {@UniqueConstraint(name = "UK_doctorId_startTime_endTime",
		columnNames = {"doctor_id", "startTime", "endTime"})})
public class TimeSlot {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Future
	private LocalDateTime startTime;
	
	@NotNull
	@Future
	private LocalDateTime endTime;
	
	private boolean isAvailable = true;
	
	@JsonBackReference
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "doctor_id")
	private Doctor doctor;
}
