package dev.Daniel.Hospital_20.model;

import dev.Daniel.Hospital_20.model.enums.Event;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Admission_log {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "bed_id")
	private Bed bed;

	@ManyToOne
	@JoinColumn(name = "patient_id")
	private Patient patient;

	private LocalDate date;
	private Event event_type;





}
