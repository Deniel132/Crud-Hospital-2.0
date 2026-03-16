package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AllPatientHospitalizedDTO {

	private String PatientName;
	private Specialty specialty;

	private LocalDateTime date;

	private Integer days;

	public AllPatientHospitalizedDTO(String PatientName, Specialty specialty, LocalDateTime date) {
		this.PatientName = PatientName;
		this.specialty = specialty;
		this.date = date;
		this.days = Math.toIntExact(ChronoUnit.DAYS.between(date, LocalDateTime.now()));
	}
}
