package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.Data;

import java.util.List;

@Data
public class Ward_DTO {



	private Long id_hospital = 0L;

	private Specialty specialty;
	private Integer room_quantity = 0;
	private Integer bed_quantity = 0;


}
