package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.Bed_DTO;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.enums.Status;
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


		@GetMapping
		public List<Bed> getAll(){return this.bedService.getAll();}

		@PostMapping
		public Bed save(@RequestBody Bed_DTO bedDto){return this.bedService.saveByDTO(bedDto);}


		@PatchMapping("/status/{id}")
		public Bed mudar_status(@PathVariable Long id){return this.bedService.setStatus(id);}


}
