package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.Bed_DTO;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.Room;
import dev.Daniel.Hospital_20.model.enums.Status;
import dev.Daniel.Hospital_20.repository.BedRepository;
import dev.Daniel.Hospital_20.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BedService {

	private final BedRepository bedRepository;
	private final RoomRepository roomRepository;


	public BedService(BedRepository bedRepository, RoomRepository roomRepository) {
		this.bedRepository = bedRepository;
		this.roomRepository = roomRepository;
	}

	public Bed saveByDTO(Bed_DTO bedDto){
		Room room = roomRepository.findById(bedDto.getRoom_id()).orElse(null);

		if (room == null){
			throw new EntityNotFoundException("Room Nao Encontrado");
		}else {
			Bed bed = new Bed();
			bed.setStatus(bedDto.getStatus());


			if (room.getBed() == null){
				bed.setBed_number(1L);
			}else{
				int i = 0;
				for (Bed b: room.getBed()){
					i++;
				}
				bed.setBed_number((i + 1L));


			}

			bedRepository.save(bed);

			bed.setRoom(room);

			List<Bed> listBed = room.getBed();
			listBed.add(bed);

			room.setBed(listBed);

			roomRepository.save(room);


			return bedRepository.save(bed);

		}
	}

	public List<Bed> saveByRoom(Integer bed_quantity, Room room){
		List<Bed> lista = new ArrayList<>();

		for (int i = 0; i < bed_quantity; i++) {
			Bed bed = new Bed();

			bed.setBed_number(i + 1L);
			bed.setRoom(room);

			lista.add(bed);



		}

		return this.bedRepository.saveAll(lista);
	}


	public List<Bed> getAll(){return this.bedRepository.findAll();}

	public Bed getById(Long id){
		Bed bed = this.bedRepository.findById(id).orElse(null);

		if (bed == null){
			throw new EntityNotFoundException("Leito Nao Encontrado");
		}else{
			return bed;
		}
	}

	public Bed ocuparLeito(Long id){
		Bed bed = getById(id);

		bed.setStatus(Status.OCCUPIED);

		return this.bedRepository.save(bed);
	}

	public Bed desOcuparLeito(Long id){
		Bed bed = getById(id);

		bed.setStatus(Status.IN_PREPARATION);

		return this.bedRepository.save(bed);
	}

	public Bed setStatus(Long id){
		Bed bed = getById(id);

		if (bed.getStatus().equals(Status.IN_PREPARATION) || bed.getStatus().equals(Status.INVALIDA)) {
			bed.setStatus((Status.UNOCCUPIED));
			return this.bedRepository.save(bed);
		}else {
			throw new RuntimeException("Nao E Possivel Alterar o status");
		}

	}


}
