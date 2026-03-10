package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.Admission_log_DTO;
import dev.Daniel.Hospital_20.model.Admission_log;
import dev.Daniel.Hospital_20.service.Admission_logService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/adimission")
public class Admission_logController {


private final Admission_logService admissionLogService;


	public Admission_logController(Admission_logService admissionLogService) {
		this.admissionLogService = admissionLogService;
	}


	@GetMapping
	public List<Admission_log> getAll(){return this.admissionLogService.getAll();}


	@PostMapping
	public Admission_log internar(@RequestBody Admission_log_DTO admissionLogDto){return this.admissionLogService.internar(admissionLogDto);}

	@PostMapping("/desinternar/{id}")
	public Admission_log desinternar(@PathVariable Long id,@RequestBody Admission_log_DTO admissionLogDto){return this.admissionLogService.desinternar(id,admissionLogDto);}




}
