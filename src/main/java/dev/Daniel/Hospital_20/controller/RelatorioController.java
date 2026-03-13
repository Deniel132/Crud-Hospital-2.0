package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.*;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.service.RelatoriosService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

	private final RelatoriosService relatoriosService;


	public RelatorioController(RelatoriosService relatoriosService) {
		this.relatoriosService = relatoriosService;
	}


	@GetMapping("/beds/{status}/{hospital_id}")
	public List<Beds_specialtyDTO> leitos_disponiveis(@PathVariable int status, @PathVariable Long hospital_id) {
		return this.relatoriosService.lista_leitos(status, hospital_id);
	}

	@GetMapping("/quantity/{hospital_id}")
	public Quantity_bedsDTO quantityBeds(@PathVariable Long hospital_id) {
		return this.relatoriosService.getQuantity(hospital_id);
	}

	@GetMapping("/internado/{id}")
	public Patient_hospitalized getPatient(@PathVariable Long id) {
		return this.relatoriosService.getPatient(id);
	}

	@GetMapping("/historico_paciente/{id}")
	public Page<Historico_DTO> historico(@PathVariable Long id, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
		return this.relatoriosService.historicoPaciente(page, size, id);
	}

	@GetMapping("/disponiveis")
	public List<Rooms_Specialty_DTO> quartos_com_disponiveis() {
		return this.relatoriosService.quartos_com_disponiveis();
	}

	@GetMapping("/internados")
	public List<All_patient_hospitalized_DTO> AllHospitalized() {
		return this.relatoriosService.getAllHospitalized();
	}

	@GetMapping("/historico_leito/{id}")
	public List<Historico_Bed_DTO> getHistory(@PathVariable Long id) {
		return this.relatoriosService.getHistory(id);
	}

	@GetMapping("/all_leitos")
	public List<Bed> AllBed() {
		return this.relatoriosService.AllBed();
	}

}
