package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.RoomDTO;
import dev.Daniel.Hospital_20.model.Room;
import dev.Daniel.Hospital_20.service.RoomService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController {

	private final RoomService roomService;

	public RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@GetMapping
	public List<Room> getAll() {
		return this.roomService.getAll();
	}

	@PostMapping("/{wardid}")
	public List<Room> save(@PathVariable Long wardid, @RequestBody RoomDTO roomDTO) {
		return this.roomService.create(wardid, roomDTO);
	}

}
