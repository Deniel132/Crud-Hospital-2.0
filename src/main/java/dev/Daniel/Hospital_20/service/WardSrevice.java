package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.HospitalDTO;
import dev.Daniel.Hospital_20.DTO.Ward_DTO;
import dev.Daniel.Hospital_20.model.Hospital;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.repository.WardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WardSrevice {

	private final WardRepository wardRepository;
	private final RoomService roomService;

	public WardSrevice(WardRepository wardRepository, RoomService roomService) {
		this.wardRepository = wardRepository;
		this.roomService = roomService;
	}


	public List<Ward> saveByHospital(HospitalDTO hospitalDTO, Hospital hospital){
		List<Ward> lista = new ArrayList<>();

		for (Ward_DTO w:hospitalDTO.getWardDtoList()){

			Ward ward = new Ward(w.getSpecialty(),hospital);

			if (w.getRoom_quantity() != null){
				ward.setRoom(roomService.saveBydWard(w.getRoom_quantity(),w.getBed_quantity(),w.getSpecialty().substring(1,3)));
			}



			lista.add(ward);
		}
		return this.wardRepository.saveAll(lista);

	}

public List<Ward> getAll(){return this.wardRepository.findAll();}





}
