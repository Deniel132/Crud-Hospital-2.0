package dev.Daniel.Hospital_20.service;


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


	public List<Ward> criarWard(List<Ward_DTO> wardDto) {

		List<Ward> wardList = new ArrayList<>();

		for (Ward_DTO w : wardDto) {
			Hospital hospital = hospitalRepository.findById(w.getId_hospital()).orElse(null);

			if (hospital == null) {
				throw new EntityNotFoundException("Hospital Nao Encontrado");

			} else {

				Ward ward = this.wardRepository.especialidade_igual(w.getSpecialty(), w.getId_hospital());


				if (ward == null) {
					ward = new Ward(w.getSpecialty(), hospital);
					this.wardRepository.save(ward);
				}


				if (w.getRoom_quantity() > 0) {

					List<Room_DTO> room_dtoList = new ArrayList<>();

					for (int i = 0; i < w.getRoom_quantity(); i++) {
						Room_DTO roomDto = new Room_DTO();
						roomDto.setBed_quantity(w.getBed_quantity());
						roomDto.setWard_id(ward.getId());

						room_dtoList.add(roomDto);
					}

					List<Room> newRooms = roomService.criarRoom(room_dtoList);
					if (ward.getRoom() == null) {
						ward.setRoom(new ArrayList<>());
					}

					for (Room r : newRooms) {
						if (!ward.getRoom().contains(r)) {
							ward.getRoom().add(r);
						}

					}
				}

				wardList.add(ward);
			}
		}

		return this.wardRepository.saveAll(wardList);
	}

	public List<Ward> getAll() {
		return this.wardRepository.findAll();
	}


}
