package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
public class BedsSpecialtyDTO {


	private Specialty specialty;
	private List<Bed> bedList;

	public BedsSpecialtyDTO(Specialty specialty, List<Bed> bedList) {
		this.specialty = specialty;
		this.bedList = bedList;
	}


}
