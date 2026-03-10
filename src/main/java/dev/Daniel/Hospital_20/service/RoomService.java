package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.Room_DTO;
import dev.Daniel.Hospital_20.model.Room;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.repository.RoomRepository;
import dev.Daniel.Hospital_20.repository.WardRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

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



	public List<Room> getAll(){return this.roomRepository.findAll();}



	public Room saveByDto(Room_DTO roomDto){
		Ward ward = wardRepository.findById(roomDto.getWard_id()).orElse(null);
		String room_code;

		if (ward == null){
			throw new EntityNotFoundException("Ward Nao Encontrado");
		}else {
			if (ward.getRoom() == null){
				 room_code = ward.getSpecialty().toString().substring(0,3) + "-" + 1;
			}else{
				int i = 1;
				for (Room r: ward.getRoom()){
					i++;
				}
				 room_code = ward.getSpecialty().toString().substring(0,3) + "-" + i;
			}



			Room room = criarRoom(roomDto.getBed_quantity(), room_code, ward);


			List<Room> roomList = ward.getRoom();
			roomList.add(room);
			ward.setRoom(roomList);

			this.wardRepository.save(ward);

			return room;
		}
	}



	public List<Room> saveBydWard(Integer room_quantity, Integer bed_quantity,Ward ward){

		List<Room> lista = new ArrayList<>();


		for (int i = 1; i <= room_quantity; i++){

			String room_code = "";

			if (ward.getSpecialty() != null) {
				room_code = ward.getSpecialty().toString().substring(0, 3).toUpperCase();
				room_code = room_code + "-" + i;
			}

			Room room = criarRoom(bed_quantity,room_code,ward);

			lista.add(room);
		}


		return this.roomRepository.saveAll(lista);
	}


	private Room criarRoom(Integer bed_quantity,String room_code,Ward ward){
		Room room = new Room();
		room.setRoom_code(room_code);
		room.setWard(ward);


		this.roomRepository.save(room);

		if (bed_quantity != null) {
			if (bed_quantity > 0) {
				room.setBed(bedService.saveByRoom(bed_quantity, room));
			}
		}
		return this.roomRepository.save(room);
	}





}
