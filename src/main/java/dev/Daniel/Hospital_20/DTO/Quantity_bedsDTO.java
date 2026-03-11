package dev.Daniel.Hospital_20.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quantity_bedsDTO {

	private Integer beds_UNOCCUPIED;
	private Integer beds_OCCUPIED;

	private List<Bed_specialty_qt_DTO> bedSpecialtyQtDto;




}
