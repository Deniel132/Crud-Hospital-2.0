package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.Room_DTO;
import dev.Daniel.Hospital_20.DTO.Ward_DTO;
import dev.Daniel.Hospital_20.model.Room;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.service.RoomService;
import dev.Daniel.Hospital_20.service.WardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {



		private  final RoomService roomService;

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

		@GetMapping
		public List<Room> getAll(){return this.roomService.getAll();}

		@PostMapping
		public List<Room> save(@RequestBody List<Room_DTO> roomDtoList){return this.roomService.criarRoom(roomDtoList);}

}
