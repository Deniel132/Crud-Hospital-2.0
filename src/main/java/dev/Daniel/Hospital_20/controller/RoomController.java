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

	@PostMapping("/{idWard}")
	public List<Room> save(@PathVariable Long idWard, @RequestBody RoomDTO roomDTO) {
		return this.roomService.create(idWard, roomDTO);
	}

}
