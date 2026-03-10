package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.HospitalDTO;
import dev.Daniel.Hospital_20.DTO.Ward_DTO;
import dev.Daniel.Hospital_20.model.Hospital;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.repository.HospitalRepository;
import dev.Daniel.Hospital_20.repository.WardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class WardService {

	private final WardRepository wardRepository;
	private final HospitalRepository hospitalRepository;
	private final RoomService roomService;

	public WardService(WardRepository wardRepository, HospitalRepository hospitalRepository, RoomService roomService) {
		this.wardRepository = wardRepository;
		this.hospitalRepository = hospitalRepository;
		this.roomService = roomService;
	}


	public Ward saveByDTO(Ward_DTO wardDto){
		Hospital hospital = hospitalRepository.findById(wardDto.getId_hospital()).orElse(null);

		if (hospital == null){
			throw new EntityNotFoundException("Hospital Nao Encontrado");
		}else {
			Ward ward = criarWard(wardDto,hospital);

			List<Ward> listaWards = hospital.getWards();
			listaWards.add(ward);

			hospital.setWards(listaWards);
			this.hospitalRepository.save(hospital);

			return ward;
		}


	}



	public List<Ward> saveByHospital(HospitalDTO hospitalDTO, Hospital hospital){
		List<Ward> lista = new ArrayList<>();

		for (Ward_DTO w:hospitalDTO.getWardDtoList()){

			Ward ward = criarWard(w,hospital);

			lista.add(ward);
		}
		return this.wardRepository.saveAll(lista);

	}



	private Ward criarWard(Ward_DTO wardDto, Hospital hospital){
		Ward ward = new Ward(wardDto.getSpecialty(),hospital);


		this.wardRepository.save(ward);
		if ( wardDto.getRoom_quantity() != null) {
			if (wardDto.getRoom_quantity() > 0) {
				ward.setRoom(roomService.saveBydWard(wardDto.getRoom_quantity(), wardDto.getBed_quantity(),ward));
			}
		}
		return this.wardRepository.save(ward);
	}

public List<Ward> getAll(){return this.wardRepository.findAll();}





}
