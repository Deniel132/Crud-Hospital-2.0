package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.Leitos_por_especialidadeDTO;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.Room;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import dev.Daniel.Hospital_20.model.enums.Status;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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


	public Map<Specialty,Leitos_por_especialidadeDTO> lista_leitos_livres(){

		Map<Specialty,Leitos_por_especialidadeDTO> lista_leitos = new HashMap<>();

		List<Ward>lista_de_Wards = this.wardService.getAll();

		for (Ward w: lista_de_Wards){

			List<Bed> bedList = new ArrayList<>();

			for (Room r: w.getRoom()){
				bedList.addAll(r.getBed().stream().filter(b -> b.getStatus() == Status.UNOCCUPIED).toList());
			}

			if (lista_leitos.containsKey(w.getSpecialty())){

				List<Bed> valores = lista_leitos.get(w.getSpecialty()).getBeds();
				valores.addAll(bedList);

				lista_leitos.get(w.getSpecialty()).setBeds(valores);
			}else {
				Leitos_por_especialidadeDTO lpe = new Leitos_por_especialidadeDTO();
				lpe.setSpecialty(w.getSpecialty());
				lpe.setBeds(bedList);
				lista_leitos.put(w.getSpecialty(),lpe);
			}

		}

		return lista_leitos;
	}






}
