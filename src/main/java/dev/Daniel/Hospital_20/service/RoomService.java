package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.Bed_DTO;
import dev.Daniel.Hospital_20.DTO.Room_DTO;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.Room;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.model.enums.Status;
import dev.Daniel.Hospital_20.repository.RoomRepository;
import dev.Daniel.Hospital_20.repository.WardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


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



	public List<Room> getAll(){return this.roomRepository.findAll();}






	public List<Room> criarRoom(List<Room_DTO> roomDto) {

		List<Room> roomList = new ArrayList<>();
		int room_number = 0;

		for (Room_DTO r : roomDto) {

			Ward ward = this.wardRepository.findById(r.getWard_id()).orElse(null);
			String room_code;

			if (ward == null) {
				throw new EntityNotFoundException("Ward Nao Encontrado");
			} else {
				if (ward.getRoom() == null) {
					room_number++;
				} else {
					room_number = 1;
					for (Room vfRoom : ward.getRoom()) {
						room_number++;
					}
				}

				room_code = ward.getSpecialty().toString().substring(0, 3) + "-" + room_number;

				Room room = new Room(room_code,ward);

				this.roomRepository.save(room);


				if (r.getBed_quantity() != null) {
					if (r.getBed_quantity() > 0) {
						List<Bed_DTO> bedDtoList = new ArrayList<>();

						for (int i = 0; i < r.getBed_quantity(); i++) {
							Bed_DTO bedDto = new Bed_DTO();
							bedDto.setRoom_id(room.getId());

							bedDtoList.add(bedDto);

						}



						room.setBed(bedService.criarBed(bedDtoList));
					}
				}
				roomList.add(room);

				List<Room> ward_room_list;

				if (ward.getRoom() == null){
					ward_room_list = new ArrayList<>();
				}else {
					ward_room_list = ward.getRoom();
				}

				ward_room_list.add(room);
				ward.setRoom(ward_room_list);
				this.wardRepository.save(ward);

			}
		}

		return this.roomRepository.saveAll(roomList);
	}


	public void verificaLeitos(){
		for (Room r: getAll()){
			if (r.getBed().stream().allMatch(b -> b.getStatus().equals(Status.OCCUPIED))){
				r.setStatus("FULL");

			}else{
				r.setStatus("DISPONIVEL");

			}
			this.roomRepository.save(r);
		}
	}


}



