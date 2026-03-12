package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient_hospitalized {

	private  String nome_hospital;
	private Specialty specialty;
	private String Room_code;
	private String nome_patient;
	private LocalTime hora;
	private LocalDate date;
}
