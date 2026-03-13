package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.model.Patient;
import dev.Daniel.Hospital_20.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {


	private final PatientService patientService;


	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}


	@GetMapping
	public List<Patient> getAll() {
		return this.patientService.getAll();
	}

	@GetMapping("/{id}")
	public Patient getById(@PathVariable Long id) {
		return this.patientService.getById(id);
	}


	@PostMapping
	public Patient save(@RequestBody Patient patient) {
		return this.patientService.save(patient);
	}


	@PutMapping("/{id}")
	public Patient atualizar_tudo(@PathVariable Long id, @RequestBody Patient patient) {
		return this.patientService.att_all(id, patient);
	}


	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		this.patientService.deleteById(id);
	}


}
