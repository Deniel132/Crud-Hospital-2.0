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

	private Long beds_UNOCCUPIED;
	private Long beds_OCCUPIED;
	private Long beds_IN_PREPARATION;

	public Quantity_bedsDTO(Long beds_UNOCCUPIED, Long beds_OCCUPIED, Long beds_IN_PREPARATION) {
		this.setBeds_UNOCCUPIED(beds_UNOCCUPIED);
		this.setBeds_OCCUPIED(beds_OCCUPIED);
		this.setBeds_IN_PREPARATION(beds_IN_PREPARATION);
	}

	private List<Bed_specialty_qt_DTO> bedSpecialtyQtDto;


}
