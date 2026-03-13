package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.Bed_DTO;
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

	@PostMapping
	public List<Bed> save(@RequestBody List<Bed_DTO> bedDtoList) {
		return this.bedService.criarBed(bedDtoList);
	}


	@PatchMapping("/status/{id}")
	public Bed mudar_status(@PathVariable Long id) {
		return this.bedService.setStatus(id);
	}

}
