package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.model.Hospital;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.service.WardSrevice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ward")
public class WardController {
	private final WardSrevice wardSrevice;

	public WardController(WardSrevice wardSrevice) {
		this.wardSrevice = wardSrevice;
	}

	@GetMapping
	public List<Ward> getAll(){return this.wardSrevice.getAll();}

}
