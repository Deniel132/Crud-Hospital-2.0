package dev.Daniel.Hospital_20.DTO;

import lombok.Data;

import java.util.List;

@Data
public class HospitalDTO {

	private String nome;
	private String phone;
	private String cnpj;

	private List<Ward_DTO> wardDtoList;
}
