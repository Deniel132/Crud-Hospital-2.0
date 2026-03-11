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


	public List<Bed> criarBed(List<Bed_DTO> bedDtoList){

		int bed_number = 0;
		List<Bed> bedList = new ArrayList<>();


		for (Bed_DTO b : bedDtoList) {

			Room room = roomRepository.findById(b.getRoom_id()).orElse(null);

			if (room == null) {
				throw new EntityNotFoundException("Room Nao Encontrado");
			} else {


				Bed bed = new Bed();

				if (room.getBed() == null) {
					bed_number ++;
				} else {
					bed_number = 1;
					for (Bed bedN : room.getBed()) {
						bed_number++;
					}
				}

				bed.setBed_number(((long) bed_number));

				this.bedRepository.save(bed);
				bed.setRoom(room);

				bedList.add(bed);

				List<Bed> listBed;

				if ( room.getBed() == null){
					listBed = new ArrayList<>();
				}else {
					listBed = room.getBed();
				}


				listBed.add(bed);
				room.setBed(listBed);
				roomRepository.save(room);

			}

		}

		return bedRepository.saveAll(bedList);
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
