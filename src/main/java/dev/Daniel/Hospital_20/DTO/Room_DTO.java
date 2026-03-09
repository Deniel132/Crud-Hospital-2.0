package dev.Daniel.Hospital_20.DTO;

import lombok.Data;

import java.util.List;

@Data
public class Room_DTO {

	private List<Bed_DTO> bed_dtoList;

	private String room_code;




}
