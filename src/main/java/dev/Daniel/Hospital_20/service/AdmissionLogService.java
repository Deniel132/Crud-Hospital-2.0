package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.AdmissionLogDTO;
import dev.Daniel.Hospital_20.model.AdmissionLog;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.Patient;
import dev.Daniel.Hospital_20.model.enums.Event;
import dev.Daniel.Hospital_20.model.enums.Status;
import dev.Daniel.Hospital_20.repository.AdmissionLogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdmissionLogService {

	private final AdmissionLogRepository admissionLogRepository;
	private final PatientService patientService;
	private final BedService bedService;
	private final RoomService roomService;

	public AdmissionLogService(AdmissionLogRepository admissionLogRepository, PatientService patientService, BedService bedService, RoomService roomService) {
		this.admissionLogRepository = admissionLogRepository;
		this.patientService = patientService;
		this.bedService = bedService;
		this.roomService = roomService;
	}

	@Transactional
	public AdmissionLog admission(AdmissionLogDTO admissionLogDto) {

		Bed bed = this.bedService.getById(admissionLogDto.getBedId());
		Patient patient = this.patientService.getById(admissionLogDto.getPatientId());

		this.validateAdmission(bed, patient, admissionLogDto);
		AdmissionLog admissionLog = new AdmissionLog(bed, patient, admissionLogDto.getTimeStamp(), Event.ADMISSION);

		this.bedService.occupyBed(admissionLogDto.getBedId());
		this.roomService.verifyBeds();
		this.patientService.admitted(admissionLogDto.getPatientId());

		return this.admissionLogRepository.save(admissionLog);
	}

	@Transactional
	public AdmissionLog discharge(Long id, AdmissionLogDTO admissionLogDto) {

		AdmissionLog admissionLogAntigo = getByIdPatient(id);
		this.validateDischarge(admissionLogAntigo);

		this.patientService.setAlta(admissionLogAntigo.getPatient().getId());
		this.bedService.vacateBed(admissionLogAntigo.getBed().getId());

		AdmissionLog admissionLogNovo = new AdmissionLog(admissionLogAntigo.getBed(), admissionLogAntigo.getPatient(),
				admissionLogDto.getTimeStamp(), Event.DISCHARGE);

		return this.admissionLogRepository.save(admissionLogNovo);
	}

	public List<AdmissionLog> getAll() {
		return this.admissionLogRepository.findAll();
	}

	public AdmissionLog getById(Long id) {
		AdmissionLog admissionLog = this.admissionLogRepository.findById(id).orElse(null);
		if (admissionLog == null) {
			throw new RuntimeException("Adimissao nao Encontrada");
		} else {
			return admissionLog;
		}
	}

	public AdmissionLog getByIdPatient(Long id) {
		return getAll().stream().filter(a -> a.getPatient().getId().equals(id)).toList().getLast();
	}

	private void validateAdmission(Bed bed, Patient patient, AdmissionLogDTO admissionLogDto) {

		if (admissionLogDto.getBedId() == null || admissionLogDto.getPatientId() == null || admissionLogDto.getTimeStamp() == null) {
			throw new RuntimeException("Internamento possui Campos Vaszios");
		}

		if (bed.getStatus().equals(Status.OCCUPIED) || bed.getStatus().equals(Status.IN_PREPARATION)) {
			throw new RuntimeException("Leito nao Disponivel");
		}

		if (patient.getIsHospitalized()) {
			throw new RuntimeException("Paciente Ja Internado");
		}
	}

	private void validateDischarge(AdmissionLog admissionLogAntigo) {
		if (admissionLogAntigo.getEventType().equals(Event.DISCHARGE)) {
			throw new RuntimeException("Cliente Ja Teve Alta");
		}
	}
}
