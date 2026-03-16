package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BedSpecialtyQtdDTO {

	private Specialty specialty;
	private Long quantity;
}
