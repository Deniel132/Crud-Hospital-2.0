package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class Beds_specialtyDTO {


	private Specialty specialty;
	private List<Bed> bedList;

	public Beds_specialtyDTO(Specialty specialty, List<Bed> bedList) {
		this.specialty = specialty;
		this.bedList = bedList;
	}


}
