package dev.Daniel.Hospital_20.model;

import dev.Daniel.Hospital_20.model.enums.Event;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdmissionLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "bed_id")
	private Bed bed;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	private LocalDateTime timeStamp;
	private Event eventType;

	public AdmissionLog(Bed bed, Patient patient, LocalDateTime timeStamp, Event eventType) {
		this.bed = bed;
		this.patient = patient;
		this.timeStamp = timeStamp;
		this.eventType = eventType;
	}
}
