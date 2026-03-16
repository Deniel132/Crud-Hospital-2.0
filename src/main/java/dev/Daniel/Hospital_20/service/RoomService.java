package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.RoomDTO;
import dev.Daniel.Hospital_20.model.Room;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.model.enums.Status;
import dev.Daniel.Hospital_20.repository.RoomRepository;
import dev.Daniel.Hospital_20.repository.WardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
public class RoomService {

	private final WardRepository wardRepository;
	private final RoomRepository roomRepository;
	private final BedService bedService;

	public RoomService(WardRepository wardRepository, RoomRepository roomRepository, BedService bedService) {
		this.wardRepository = wardRepository;
		this.roomRepository = roomRepository;
		this.bedService = bedService;
	}

	public List<Room> getAll() {
		return this.roomRepository.findAll();
	}

	@Transactional
	public List<Room> create(Long wardId, RoomDTO roomDTOS) {
		Ward ward = this.wardRepository.findById(wardId).orElseThrow(() -> new RuntimeException("Ward Nao Encontrada"));
		return this.generate(ward, roomDTOS.getRoomQuantity(), roomDTOS.getBedQuantity());
	}

	@Transactional
	public List<Room> generate(Ward ward, Integer roomQuantity, Integer bedQuantity) {

		List<Room> roomList = new ArrayList<>();

		for (int i = 1; i <= roomQuantity; i++) {
			String roomCode;

			if (this.roomRepository.getNumberFromRumCode(ward.getId()) != null) {
				roomCode = ward.getSpecialty().toString().substring(0, 3) + "-" + (this.roomRepository.getNumberFromRumCode(ward.getId()) + 1);
			} else {
				roomCode = ward.getSpecialty().toString().substring(0, 3) + "-" + i;
			}

			Room room = new Room(roomCode, ward);
			this.roomRepository.save(room);

			if (bedQuantity != null && bedQuantity > 0) {
				room.setBeds(this.bedService.generate(room, bedQuantity));
			}

			roomList.add(room);
		}
		return this.roomRepository.saveAll(roomList);
	}


	public void verifyBeds() {
		for (Room r : getAll()) {
			if (r.getBeds().stream().allMatch(b -> b.getStatus().equals(Status.OCCUPIED))) {
				r.setFilled(true);
			} else {
				r.setFilled(false);
			}
			this.roomRepository.save(r);
		}
	}


}



