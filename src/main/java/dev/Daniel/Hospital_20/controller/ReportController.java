package dev.Daniel.Hospital_20.controller;

import dev.Daniel.Hospital_20.DTO.*;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.service.ReportService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/report")
public class ReportController {

	private final ReportService reportService;

	public ReportController(ReportService reportService) {
		this.reportService = reportService;
	}

	@GetMapping("/beds/{status}/{hospitalId}")
	public List<BedsSpecialtyDTO> freeBeds(@PathVariable int status, @PathVariable Long hospitalId) {
		return this.reportService.bedList(status, hospitalId);
	}

	@GetMapping("/quantity/{hospitalId}")
	public QuantityBedsDTO quantityBeds(@PathVariable Long hospitalId) {
		return this.reportService.getQuantity(hospitalId);
	}

	@GetMapping("/hospitalized/{id}")
	public PatientHospitalized getPatient(@PathVariable Long id) {
		return this.reportService.getPatient(id);
	}

	@GetMapping("/history/{id}")
	public Page<HistoryDTO> history(@PathVariable Long id, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "5") int size) {
		return this.reportService.patientHistory(page, size, id);
	}

	@GetMapping("/available/{hospitalid}")
	public List<RoomsSpecialtyDTO> available(@PathVariable Long hospitalid) {
		return this.reportService.avaiable(hospitalid);
	}

	@GetMapping("/allhospitalized/{hospitaid}")
	public List<AllPatientHospitalizedDTO> AllHospitalized(@PathVariable Long hospitaid) {
		return this.reportService.getAllHospitalized(hospitaid);
	}

	@GetMapping("/bed-history/{id}")
	public List<BedHistoryDTO> getHistory(@PathVariable Long id) {
		return this.reportService.getHistory(id);
	}

	@GetMapping("/all-beds")
	public List<Bed> AllBed() {
		return this.reportService.AllBed();
	}
}
