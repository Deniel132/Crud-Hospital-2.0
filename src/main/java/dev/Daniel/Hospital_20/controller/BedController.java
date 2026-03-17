package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.BedDTO;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.service.BedService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bed")
public class BedController {


	private final BedService bedService;

	public BedController(BedService bedService) {
		this.bedService = bedService;
	}

	@GetMapping("/{id}")
	public Bed getById(@PathVariable Long id) {
		return this.bedService.getById(id);
	}

	@GetMapping
	public List<Bed> getAll() {
		return this.bedService.getAll();
	}

	@PostMapping("/{roomid}")
	public List<Bed> create(@PathVariable Long roomid, @RequestBody BedDTO bedDTO) {
		return this.bedService.create(roomid, bedDTO);
	}


	@PatchMapping("/status/{id}")
	public Bed update(@PathVariable Long id) {
		return this.bedService.updateStatus(id);
	}

}
