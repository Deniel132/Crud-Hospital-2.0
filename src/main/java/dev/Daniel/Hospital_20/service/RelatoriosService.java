package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.*;
import dev.Daniel.Hospital_20.model.*;
import dev.Daniel.Hospital_20.model.enums.Event;
import dev.Daniel.Hospital_20.model.enums.Status;
import dev.Daniel.Hospital_20.repository.Admission_logRepository;
import dev.Daniel.Hospital_20.repository.BedRepository;
import dev.Daniel.Hospital_20.repository.RoomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RelatoriosService {

	private final Admission_logService admissionLogService;
	private final BedService bedService;
	private final RoomRepository roomRepository;
	private final BedRepository bedRepository;
	private final Admission_logRepository logRepository;


	public RelatoriosService(Admission_logService admissionLogService, BedService bedService, RoomRepository roomRepository, BedRepository bedRepository, Admission_logRepository logRepository) {
		this.admissionLogService = admissionLogService;
		this.bedService = bedService;
		this.roomRepository = roomRepository;
		this.bedRepository = bedRepository;
		this.logRepository = logRepository;
	}


	public List<Beds_specialtyDTO> lista_leitos(int status_int, Long hospital_id) {
		return this.bedRepository.leitos_speciality(Status.deint(status_int), hospital_id);
	}


	public Bed bed_patient(Long id) {
		Admission_log log = this.admissionLogService.getByIdPatient(id);

		if (log.getEvent_type() == Event.DISCHARGE) {
			throw new RuntimeException("PAtciente ja Recebeu Alta");
		} else {
			return log.getBed();
		}
	}

	public List<Bed> AllBed() {
		return this.bedService.getAll();
	}

	public Quantity_bedsDTO getQuantity(Long hospital_id) {
		Quantity_bedsDTO quantityBedsDTO = this.bedRepository.quantidade(hospital_id);
		quantityBedsDTO.setBedSpecialtyQtDto(this.bedRepository.qunatidadePorAla(hospital_id));
		return quantityBedsDTO;
	}

	public Patient_hospitalized getPatient(Long id) {
		return this.logRepository.patienteInfo(id);
	}


	public Page<Historico_DTO> historicoPaciente(int pagina, int tamanho, Long id) {
		Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("date").descending());
		return logRepository.historico(id, pageable);
	}


	public List<Rooms_Specialty_DTO> quartos_com_disponiveis() {
		return this.roomRepository.quartos_com_disponiveis();
	}

	public List<All_patient_hospitalized_DTO> getAllHospitalized() {
		return this.logRepository.All_hospitalized();
	}


	public List<Historico_Bed_DTO> getHistory(Long id) {
		return this.logRepository.historico_Bed(id);
	}


}
