package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.HospitalDTO;
import dev.Daniel.Hospital_20.DTO.Ward_DTO;
import dev.Daniel.Hospital_20.model.Hospital;
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
	public List<Ward> getAll(){return this.wardService.getAll();}

	@PostMapping
	public List<Ward> save(@RequestBody List<Ward_DTO> wardDtoList){return this.wardService.criarWard(wardDtoList);}

}
