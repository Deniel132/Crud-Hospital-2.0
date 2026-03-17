package dev.Daniel.Hospital_20.service;

import dev.Daniel.Hospital_20.DTO.BedDTO;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.Room;
import dev.Daniel.Hospital_20.model.enums.Status;
import dev.Daniel.Hospital_20.repository.BedRepository;
import dev.Daniel.Hospital_20.repository.RoomRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


	@Transactional
	public List<Bed> create(Long roomId, BedDTO bedDTO) {
		Room room = roomRepository.findById(roomId).orElseThrow(() -> new RuntimeException("Room Nao Encontrado"));
		return this.generate(room, bedDTO.getQuantity());
	}


	@Transactional
	public List<Bed> generate(Room room, Integer bedQuantity) {

		List<Bed> bedList = new ArrayList<>();

		long nextNumber = (bedRepository.getBedNumber(room.getId()) == null) ? 1 : bedRepository.getBedNumber(room.getId()) + 1;

		for (int i = 1; i <= bedQuantity; i++) {
			Bed bed = new Bed();
			bed.setRoom(room);
			bed.setBedNumber(nextNumber++);
			bedList.add(bed);
		}
		return bedRepository.saveAll(bedList);
	}


	public List<Bed> getAll() {
		return this.bedRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Bed getById(Long id) {
		return this.bedRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Leito Nao Encontrado"));
	}

	@Transactional
	public void occupyBed(Long id) {
		Bed bed = getById(id);
		bed.setStatus(Status.OCCUPIED);
		this.bedRepository.save(bed);
	}

	@Transactional
	public void vacateBed(Long id) {
		Bed bed = getById(id);
		bed.setStatus(Status.IN_PREPARATION);
		this.bedRepository.save(bed);
	}

	@Transactional
	public Bed updateStatus(Long id) {
		Bed bed = getById(id);
		if (bed.getStatus().equals(Status.IN_PREPARATION) || bed.getStatus().equals(Status.INVALIDA)) {
			bed.setStatus((Status.UNOCCUPIED));
			return this.bedRepository.save(bed);
		} else {
			throw new RuntimeException("Nao E Possivel Alterar o status");
		}
	}


}
