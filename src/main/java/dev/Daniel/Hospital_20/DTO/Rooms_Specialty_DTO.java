package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.Data;

import java.util.List;

@Data
public class Rooms_Specialty_DTO {

	private Specialty specialty;
	private List<String> room_code;


}
