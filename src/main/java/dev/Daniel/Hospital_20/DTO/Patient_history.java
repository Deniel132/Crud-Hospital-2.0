package dev.Daniel.Hospital_20.DTO;

import lombok.Data;

import java.util.List;

@Data
public class Patient_history {

	private String nome_patient;

	private List<Historico_DTO> historico;


}
