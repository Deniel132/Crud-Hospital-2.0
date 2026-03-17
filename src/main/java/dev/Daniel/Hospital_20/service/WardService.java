package dev.Daniel.Hospital_20.service;


import dev.Daniel.Hospital_20.DTO.WardDTO;
import dev.Daniel.Hospital_20.model.Hospital;
import dev.Daniel.Hospital_20.model.Room;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.repository.HospitalRepository;
import dev.Daniel.Hospital_20.repository.WardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Transactional
	public List<Ward> create(Long id, List<WardDTO> wardDTOS) {
		Hospital hospital = this.hospitalRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Hospital nao Existente"));
		return this.generate(hospital, wardDTOS);
	}

	@Transactional
	public List<Ward> generate(Hospital hospital, List<WardDTO> wardDto) {
		List<Ward> wardList = new ArrayList<>();
		for (WardDTO w : wardDto) {

			Ward ward = this.wardRepository.specialtyEquals(w.getSpecialty(), hospital.getId());

			if (ward == null) {
				ward = new Ward(w.getSpecialty(), hospital);
			}
			this.wardRepository.save(ward);

			if (w.getRoomQuantity() > 0) {
				if (ward.getRooms() != null) {
					List<Room> rooms = this.roomService.generate(ward, w.getRoomQuantity(), w.getBedQuantity());

					for (Room r : rooms) {
						if (!ward.getRooms().contains(r)) {
							ward.getRooms().add(r);
						}
					}
				} else {
					ward.setRooms(this.roomService.generate(ward, w.getRoomQuantity(), w.getBedQuantity()));
				}
			}
			wardList.add(ward);
		}
		return this.wardRepository.saveAll(wardList);
	}

	public List<Ward> getAll() {
		return this.wardRepository.findAll();
	}


}
