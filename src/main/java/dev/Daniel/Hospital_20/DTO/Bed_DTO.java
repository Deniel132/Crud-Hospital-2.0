package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.enums.Status;
import lombok.Data;

@Data
public class Bed_DTO {

	private String bed_number;
	private Status status = Status.UNOCCUPIED;

	private Long room_id = 0L;
}
