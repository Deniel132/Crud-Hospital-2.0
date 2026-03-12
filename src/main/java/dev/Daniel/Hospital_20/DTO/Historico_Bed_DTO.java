package dev.Daniel.Hospital_20.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historico_Bed_DTO {

	private String nome_patient;

	private Long bed_id;

	private LocalDate data_Internamento;

	private LocalDate data_alta;

}
