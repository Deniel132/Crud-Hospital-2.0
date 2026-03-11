package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class Historico_DTO {


	private Specialty specialty;
	private LocalDate data_internamento;
	private LocalDate data_alta;





}
