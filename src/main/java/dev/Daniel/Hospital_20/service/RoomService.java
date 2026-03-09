package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.HospitalDTO;
import dev.Daniel.Hospital_20.model.Room;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoomService {

	private final RoomRepository roomRepository;


	public RoomService(RoomRepository roomRepository) {
		this.roomRepository = roomRepository;
	}

	public List<Room> saveBydWard(Integer room_quantity, Integer bed_quantity,String room_code){

		List<Room> lista = new ArrayList<>();


		for (int i = 0; i < room_quantity; i++){

			Room room = new Room();
			room.setRoom_code(room_code + i);


		}


		return roomRepository.saveAll(lista);

	}







}
