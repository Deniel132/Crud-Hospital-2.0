package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.enums.Event;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class Admission_log_DTO {

	private Long leito_id;
	private Long patient_id;

	private LocalDate date;
	private LocalTime hora;




}
