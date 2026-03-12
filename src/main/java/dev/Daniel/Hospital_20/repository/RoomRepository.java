package dev.Daniel.Hospital_20.repository;


import dev.Daniel.Hospital_20.DTO.Rooms_Specialty_DTO;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.Room;
import dev.Daniel.Hospital_20.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Long> {

	@Query("SELECT new dev.Daniel.Hospital_20.DTO.Rooms_Specialty_DTO(r.ward.specialty,r.room_code)" +
			"FROM Room r " +
			"where r.status = 'DISPONIVEL'")
	public List<Rooms_Specialty_DTO> quartos_com_disponiveis();

}
