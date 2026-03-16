package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.AdmissionLogDTO;
import dev.Daniel.Hospital_20.model.AdmissionLog;
import dev.Daniel.Hospital_20.service.AdmissionLogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admission")
public class AdmissionLogController {


	private final AdmissionLogService admissionLogService;

	public AdmissionLogController(AdmissionLogService admissionLogService) {
		this.admissionLogService = admissionLogService;
	}

	@GetMapping
	public List<AdmissionLog> getAll() {
		return this.admissionLogService.getAll();
	}


	@PostMapping
	public AdmissionLog admission(@RequestBody AdmissionLogDTO admissionLogDto) {
		return this.admissionLogService.admission(admissionLogDto);
	}

	@PostMapping("/discharge/{id}")
	public AdmissionLog discharge(@PathVariable Long id, @RequestBody AdmissionLogDTO admissionLogDto) {
		return this.admissionLogService.discharge(id, admissionLogDto);
	}

}
