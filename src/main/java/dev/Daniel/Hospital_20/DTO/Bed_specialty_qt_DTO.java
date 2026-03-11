package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.Data;

import java.util.List;

@Data
public class Bed_specialty_qt_DTO {


	private Specialty specialty;
	private Integer quantity;



}
