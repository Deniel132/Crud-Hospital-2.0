package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class All_patient_hospitalized_DTO {

	private String nome_Patient;
	private Specialty specialty;

	private LocalDate date;

	private Integer days;

	public All_patient_hospitalized_DTO(String nome_Patient, Specialty specialty, LocalDate date) {
		this.nome_Patient = nome_Patient;
		this.specialty = specialty;
		this.date = date;
		this.days = Math.toIntExact(ChronoUnit.DAYS.between(date, LocalDate.now()));
	}
}
