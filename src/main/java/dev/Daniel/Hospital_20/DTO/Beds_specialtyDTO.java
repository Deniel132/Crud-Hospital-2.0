package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.Data;

import java.util.List;

@Data
public class Beds_specialtyDTO {


	private Specialty specialty;
	private List<Bed> bedList;

}
