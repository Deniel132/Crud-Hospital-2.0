package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.*;
import dev.Daniel.Hospital_20.model.*;
import dev.Daniel.Hospital_20.model.enums.Event;
import dev.Daniel.Hospital_20.model.enums.Status;
import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class RelatoriosService {

	private final Admission_logService admissionLogService;
	private final HospitalService hospitalService;
	private final WardService wardService;
	private final RoomService roomService;
	private final BedService bedService;
	private final PatientService patientService;



	public RelatoriosService(Admission_logService admissionLogService, HospitalService hospitalService, WardService wardService, RoomService roomService, BedService bedService, PatientService patientService) {
		this.admissionLogService = admissionLogService;
		this.hospitalService = hospitalService;
		this.wardService = wardService;
		this.roomService = roomService;
		this.bedService = bedService;
		this.patientService = patientService;
	}


	public List<Beds_specialtyDTO> lista_leitos(int status_int){

		Status status = Status.deint(status_int);

		List<Beds_specialtyDTO> bedsSpecialty = new ArrayList<>();
		List<Ward>lista_de_Wards = this.wardService.getAll();

		for (Ward w: lista_de_Wards){

			List<Bed> bedList = new ArrayList<>();

			for (Room r: w.getRoom()){
				if (status_int > 3) {
					bedList.addAll(r.getBed().stream().toList());
				}else{
					bedList.addAll(r.getBed().stream().filter(b -> b.getStatus() == status).toList());
				}
			}
			if (bedsSpecialty.stream().anyMatch(f -> f.getSpecialty().equals(w.getSpecialty()))){
				bedsSpecialty.stream().filter(s -> s.getSpecialty()
						.equals(w.getSpecialty())).toList().getFirst().getBedList().addAll(bedList);
			}else{
				Beds_specialtyDTO dto = new Beds_specialtyDTO();
				dto.setSpecialty(w.getSpecialty());
				dto.setBedList(bedList);

				bedsSpecialty.add(dto);
			}
		}

		return bedsSpecialty;
	}


	public Bed bed_patient(Long id){
		Admission_log log = this.admissionLogService.getByIdPatient(id);

		if (log.getEvent_type() == Event.DISCHARGE){
			throw new RuntimeException("PAtciente ja Recebeu Alta");
		}else{
			return log.getBed();
		}
	}

	public List<Bed> AllBed(){return this.bedService.getAll();}

	public Quantity_bedsDTO getQuantity(){
		Quantity_bedsDTO quantityBedsDTO = new Quantity_bedsDTO();

		Integer UNOCCUPIED = 0;
		Integer OCCUPIED = 0;


		for(Bed b : this.bedService.getAll()){
			if (b.getStatus().equals(Status.UNOCCUPIED)){
				UNOCCUPIED ++;
			} else if (b.getStatus().equals(Status.OCCUPIED)) {
				OCCUPIED ++;
			}
		}

		quantityBedsDTO.setBeds_UNOCCUPIED(UNOCCUPIED);
		quantityBedsDTO.setBeds_OCCUPIED(OCCUPIED);

		List<Bed_specialty_qt_DTO> list = new ArrayList<>();

		List<Beds_specialtyDTO> dtoList = lista_leitos(4);

		for (Beds_specialtyDTO dto : dtoList){
			Bed_specialty_qt_DTO qt_dto = new Bed_specialty_qt_DTO();

			qt_dto.setSpecialty(dto.getSpecialty());
			qt_dto.setQuantity(dto.getBedList().size());

			list.add(qt_dto);
		}
		quantityBedsDTO.setBedSpecialtyQtDto(list);
		return quantityBedsDTO;
	}

	public Patient_hospitalized getPatient(Long id){
		Admission_log log = this.admissionLogService.getByIdPatient(id);

		if (log.getPatient().getIs_hospitalized()) {

			Patient_hospitalized hospitalized = new Patient_hospitalized();

			hospitalized.setNome_patient(log.getPatient().getName());
			hospitalized.setDate(log.getDate());
			hospitalized.setHora(log.getHora());
			hospitalized.setRoom_code(log.getBed().getRoom().getRoom_code());
			hospitalized.setSpecialty(log.getBed().getRoom().getWard().getSpecialty());
			hospitalized.setNome_hospital(log.getBed().getRoom().getWard().getHospital().getNome());

			return hospitalized;
		}else{
			throw new EntityNotFoundException("Paciente Nao Esta Internado");
		}
	}



	public Patient_history historico_paciente(Integer pagina,Long id){

		Patient_history patientHistory = new Patient_history();

		patientHistory.setNome_patient(this.patientService.getById(id).getName());

		List<Historico_DTO> historicoDtoList = new ArrayList<>();


		int logsPorPagina = 2;
		int internamento = (pagina - 1) * logsPorPagina;
		int alta = internamento + 1;



		List<Admission_log> logList = this.admissionLogService.getAll().stream()
				.filter(l ->l.getPatient().getId().equals(id)).toList();


		if (internamento >= logList.size()){
			throw new RuntimeException("Paciente Ainda Nao Possui essa Pagina no Historico");
		}else {

			Historico_DTO historicoDto = getHistoricoDto(logList, internamento, alta);
			historicoDtoList.add(historicoDto);

			patientHistory.setHistorico(historicoDtoList);

			return patientHistory;
		}
	}

	private  Historico_DTO getHistoricoDto(List<Admission_log> logList, int internamento, int alta) {
		Historico_DTO historicoDto = new Historico_DTO();

		historicoDto.setData_internamento(logList.get(internamento).getDate());

		if (alta < logList.size() && logList.get(alta).getEvent_type().equals(Event.DISCHARGE)) {
			historicoDto.setData_alta(logList.get(alta).getDate());
			historicoDto.setSpecialty(logList.get(alta).getBed().getRoom().getWard().getSpecialty());
		} else {
			historicoDto.setData_alta(null);
			historicoDto.setSpecialty(logList.get(internamento).getBed().getRoom().getWard().getSpecialty());
		}
		return historicoDto;
	}


	public List<Rooms_Specialty_DTO> quartos_com_disponiveis(){


		List<Rooms_Specialty_DTO> roomsSpecialtyList = new ArrayList<>();



		for (Ward w: this.wardService.getAll()) {

			List<String> room_codes = new ArrayList<>();



			for (Room r : this.roomService.getAll().stream().filter(r -> r.getWard().getId().equals(w.getId())).toList()) {
				if (!r.getStatus().equals("FULL")) {
					room_codes.add(r.getRoom_code());
				}
			}


			if (roomsSpecialtyList.stream().anyMatch(r -> r.getSpecialty().equals(w.getSpecialty()))){

				roomsSpecialtyList.stream().filter(r -> r.getSpecialty()
						.equals(w.getSpecialty())).toList().getFirst().getRoom_code().addAll(room_codes);
			}else{
				Rooms_Specialty_DTO roomsSpecialtyDto = new Rooms_Specialty_DTO();
				roomsSpecialtyDto.setRoom_code(room_codes);
				roomsSpecialtyDto.setSpecialty(w.getSpecialty());

				roomsSpecialtyList.add(roomsSpecialtyDto);
			}

		}


		return roomsSpecialtyList;
	}


}
