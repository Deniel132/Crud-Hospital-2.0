package dev.Daniel.Hospital_20.repository;


import dev.Daniel.Hospital_20.DTO.RoomsSpecialtyDTO;
import dev.Daniel.Hospital_20.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

	@Query("SELECT new dev.Daniel.Hospital_20.DTO.RoomsSpecialtyDTO(r.ward.specialty,r.roomCode)" +
			"FROM Room r " +
			"where r.isFilled = false")
	public List<RoomsSpecialtyDTO> freeRooms();

	@Query(value = """
			select max(cast(substring(r.room_code from '[0-9]+') as integer))
			from room r
			where r.ward_id = :wardId
			""", nativeQuery = true)
	public Integer getNumberFromRumCode(Long wardId);

}
