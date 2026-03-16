package dev.Daniel.Hospital_20.controller;


import dev.Daniel.Hospital_20.DTO.WardDTO;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.service.WardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ward")
public class WardController {

	private final WardService wardService;

	public WardController(WardService wardService) {
		this.wardService = wardService;
	}

	@GetMapping
	public List<Ward> getAll() {
		return this.wardService.getAll();
	}

	@PostMapping("/{hospitalid}")
	public List<Ward> save(@PathVariable Long hospitalid, @RequestBody List<WardDTO> wardDtoList) {
		return this.wardService.create(hospitalid, wardDtoList);
	}
}
