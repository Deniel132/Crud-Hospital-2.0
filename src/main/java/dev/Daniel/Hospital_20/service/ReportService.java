package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.*;
import dev.Daniel.Hospital_20.model.AdmissionLog;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.enums.Event;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import dev.Daniel.Hospital_20.model.enums.Status;
import dev.Daniel.Hospital_20.repository.AdmissionLogRepository;
import dev.Daniel.Hospital_20.repository.BedRepository;
import dev.Daniel.Hospital_20.repository.RoomRepository;
import dev.Daniel.Hospital_20.repository.WardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ReportService {

	private final AdmissionLogService admissionLogService;
	private final BedService bedService;
	private final RoomRepository roomRepository;
	private final BedRepository bedRepository;
	private final AdmissionLogRepository logRepository;
	private final WardRepository wardRepository;


	public ReportService(AdmissionLogService admissionLogService, BedService bedService, RoomRepository roomRepository, BedRepository bedRepository, AdmissionLogRepository logRepository, WardRepository wardRepository) {
		this.admissionLogService = admissionLogService;
		this.bedService = bedService;
		this.roomRepository = roomRepository;
		this.bedRepository = bedRepository;
		this.logRepository = logRepository;
		this.wardRepository = wardRepository;
	}


	public List<BedsSpecialtyDTO> bedList(int status_int, Long hospital_id) {
		List<BedsSpecialtyDTO> list = new ArrayList<>();

		for (Specialty sp : wardRepository.specialtyList(hospital_id)) {
			BedsSpecialtyDTO bedsSpecialtyDTO = new BedsSpecialtyDTO();

			List<Bed> bedList = this.bedRepository.bedSpecialty(Status.deint(status_int), hospital_id, sp);

			bedsSpecialtyDTO.setSpecialty(sp);
			bedsSpecialtyDTO.setBedList(bedList);
			list.add(bedsSpecialtyDTO);
		}

		return list;
	}


	public Bed bed_patient(Long id) {
		AdmissionLog log = this.admissionLogService.getByIdPatient(id);

		if (log.getEventType() == Event.DISCHARGE) {
			throw new RuntimeException("PAtciente ja Recebeu Alta");
		} else {
			return log.getBed();
		}
	}

	public List<Bed> AllBed() {
		return this.bedService.getAll();
	}

	public QuantityBedsDTO getQuantity(Long hospital_id) {
		QuantityBedsDTO quantityBedsDTO = this.bedRepository.quantity(hospital_id);
		quantityBedsDTO.setBedSpecialtyQtDto(this.bedRepository.quantityPerWard(hospital_id));
		return quantityBedsDTO;
	}

	public PatientHospitalized getPatient(Long id) {
		return this.logRepository.patientInfo(id);
	}


	public Page<HistoryDTO> patientHistory(int pagina, int tamanho, Long id) {
		Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by("date").descending());
		return logRepository.history(id, pageable);
	}


	public List<RoomsSpecialtyDTO> avaiable() {
		return this.roomRepository.freeRooms();
	}

	public List<AllPatientHospitalizedDTO> getAllHospitalized() {
		return this.logRepository.All_hospitalized();
	}


	public List<BedHistoryDTO> getHistory(Long id) {
		return this.logRepository.bedHistory(id);
	}


}
