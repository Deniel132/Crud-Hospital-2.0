package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.Admission_log_DTO;
import dev.Daniel.Hospital_20.model.Admission_log;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.Patient;
import dev.Daniel.Hospital_20.model.enums.Event;
import dev.Daniel.Hospital_20.model.enums.Status;
import dev.Daniel.Hospital_20.repository.Admission_logRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Admission_logService {

	private final Admission_logRepository admissionLogRepository;
	private final PatientService patientService;
	private final BedService bedService;


	public Admission_logService(Admission_logRepository admissionLogRepository, PatientService patientService, BedService bedService) {
		this.admissionLogRepository = admissionLogRepository;
		this.patientService = patientService;
		this.bedService = bedService;
	}


	public Admission_log internar(Admission_log_DTO admissionLogDto){

		if (admissionLogDto.getLeito_id() == null || admissionLogDto.getPatient_id() == null || admissionLogDto.getDate() == null ){
			throw new RuntimeException("Internamento possui Campos Vaszios");
		}

		Bed bed = this.bedService.getById(admissionLogDto.getLeito_id());

		if (bed.getStatus().equals(Status.OCCUPIED)){
			throw new RuntimeException("Leito Ja Ocupado");
		}else {

			Patient patient = this.patientService.getById(admissionLogDto.getPatient_id());

			if (patient.getIs_hospitalized()){
				throw new RuntimeException("Paciente Ja Internado");
			}else {
				Admission_log admissionLog = new Admission_log();

				admissionLog.setDate(admissionLogDto.getDate());
				admissionLog.setEvent_type(Event.ADMISSION);
				admissionLog.setPatient(patient);
				admissionLog.setBed(bed);

				this.bedService.ocuparLeito(admissionLogDto.getLeito_id());
				this.patientService.setStatus(admissionLogDto.getPatient_id());

				return this.admissionLogRepository.save(admissionLog);
			}
		}
	}


	public Admission_log desinternar(Long id,Admission_log_DTO admissionLogDto){
		Admission_log admissionLogAntigo = getById(id);

		this.patientService.setStatus(admissionLogAntigo.getPatient().getId());
		this.bedService.desOcuparLeito(admissionLogAntigo.getBed().getId());

		Admission_log admission_logNovo = new Admission_log();

		admission_logNovo.setDate(admissionLogDto.getDate());
		admission_logNovo.setEvent_type(Event.DISCHARGE);
		admission_logNovo.setPatient(admissionLogAntigo.getPatient());
		admission_logNovo.setBed(admissionLogAntigo.getBed());


	return this.admissionLogRepository.save(admission_logNovo);

	}

	public List<Admission_log> getAll(){return this.admissionLogRepository.findAll();}

	public Admission_log getById(Long id){
		Admission_log admissionLog = this.admissionLogRepository.findById(id).orElse(null);

		if (admissionLog == null){
			throw new RuntimeException("Adimissao nao Encontrada");
		}else{
			return admissionLog;
		}
	}


}
