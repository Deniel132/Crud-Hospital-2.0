package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.HospitalDTO;
import dev.Daniel.Hospital_20.DTO.Room_DTO;
import dev.Daniel.Hospital_20.DTO.Ward_DTO;
import dev.Daniel.Hospital_20.model.Hospital;
import dev.Daniel.Hospital_20.model.Room;
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



	public List<Ward> criarWard(List<Ward_DTO> wardDto){

		List<Ward> wardList = new ArrayList<>();

		for (Ward_DTO w : wardDto){
			Hospital hospital = hospitalRepository.findById(w.getId_hospital()).orElse(null);

			if (hospital == null){
				throw new EntityNotFoundException("Hospital Nao Encontrado");
			}else {

				Ward ward = new Ward(w.getSpecialty(), hospital);

				this.wardRepository.save(ward);
				if (w.getRoom_quantity() != null) {
					if (w.getRoom_quantity() > 0) {
						List<Room_DTO> room_dtoList = new ArrayList<>();

						for (int i = 0; i < w.getRoom_quantity(); i++) {
							Room_DTO roomDto = new Room_DTO();
							roomDto.setBed_quantity(w.getBed_quantity());
							roomDto.setWard_id(ward.getId());

							room_dtoList.add(roomDto);
						}

						ward.setRoom(roomService.criarRoom(room_dtoList));
					}
				}
				wardList.add(ward);
			}
		}

		return this.wardRepository.saveAll(wardList);
	}

public List<Ward> getAll(){return this.wardRepository.findAll();}





}
