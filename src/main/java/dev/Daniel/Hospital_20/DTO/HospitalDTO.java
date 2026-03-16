package dev.Daniel.Hospital_20.DTO;

import lombok.Data;

import java.util.List;

@Data
public class HospitalDTO {

	private String name;
	private String phone;
	private String cnpj;

	private List<WardDTO> wardDtoList;
}
