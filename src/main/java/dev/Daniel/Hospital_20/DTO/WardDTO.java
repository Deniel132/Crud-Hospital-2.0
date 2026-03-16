package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.Data;

@Data
public class WardDTO {

	private Specialty specialty;
	private Integer roomQuantity = 0;
	private Integer bedQuantity = 0;
}
