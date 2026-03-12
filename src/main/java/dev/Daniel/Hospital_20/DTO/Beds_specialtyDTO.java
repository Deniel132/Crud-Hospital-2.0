package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Beds_specialtyDTO {


	private Specialty specialty;
	private Bed bedList;

	public Beds_specialtyDTO(Specialty specialty, Bed bedList) {
		this.specialty = specialty;
		this.bedList = bedList;
	}

}
