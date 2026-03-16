package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.HospitalDTO;
import dev.Daniel.Hospital_20.model.Hospital;
import dev.Daniel.Hospital_20.service.HospitalService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

	private final HospitalService hospitalService;

	public HospitalController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
	}

	@PostMapping
	public Hospital save(@RequestBody HospitalDTO hospitalDTO) {
		return this.hospitalService.create(hospitalDTO);
	}

	@GetMapping
	public List<Hospital> getAll() {
		return this.hospitalService.getAll();
	}

	@GetMapping("/{id}")
	public Hospital getById(@PathVariable Long id) {
		return this.hospitalService.getById(id);
	}

	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Long id) {
		this.hospitalService.deleteById(id);
	}
}
