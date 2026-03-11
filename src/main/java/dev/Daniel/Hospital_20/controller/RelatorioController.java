package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.*;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import dev.Daniel.Hospital_20.service.RelatoriosService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController {

	private final RelatoriosService relatoriosService;


	public RelatorioController(RelatoriosService relatoriosService) {
		this.relatoriosService = relatoriosService;
	}




	@GetMapping("/beds/{status}")
	public List<Beds_specialtyDTO> leitos_disponiveis(@PathVariable int status){return this.relatoriosService.lista_leitos(status);}

	@GetMapping("/quantity")
	public Quantity_bedsDTO quantityBeds(){return this.relatoriosService.getQuantity();}

	@GetMapping("/internado/{id}")
	public Patient_hospitalized getPatient(@PathVariable Long id){return this.relatoriosService.getPatient(id);}

	@GetMapping("/historico/{pagina}/{id}")
	public Patient_history getHistory(@PathVariable int pagina,@PathVariable Long id){return this.relatoriosService.historico_paciente(pagina,id);}

	@GetMapping("/disponiveis")
	public List<Rooms_Specialty_DTO> quartos_com_disponiveis(){return this.relatoriosService.quartos_com_disponiveis();}


}
