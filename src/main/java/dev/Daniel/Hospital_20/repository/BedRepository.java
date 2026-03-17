package dev.Daniel.Hospital_20.repository;

import dev.Daniel.Hospital_20.DTO.BedSpecialtyQtdDTO;
import dev.Daniel.Hospital_20.DTO.QuantityBedsDTO;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import dev.Daniel.Hospital_20.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BedRepository extends JpaRepository<Bed, Long> {


	@Query("SELECT new dev.Daniel.Hospital_20.DTO.QuantityBedsDTO( " +
			"COUNT (CASE WHEN b.status = dev.Daniel.Hospital_20.model.enums.Status.UNOCCUPIED THEN 1 ELSE NULL END), " +
			"COUNT (CASE WHEN b.status = dev.Daniel.Hospital_20.model.enums.Status.OCCUPIED  THEN 1 ELSE NULL END)," +
			"COUNT (CASE WHEN b.status = dev.Daniel.Hospital_20.model.enums.Status.IN_PREPARATION  THEN 1 ELSE NULL END)) " +
			"FROM Bed b " +
			"WHERE b.room.ward.hospital.id = :hospitalId ")

	public QuantityBedsDTO quantity(Long hospitalId);

	@Query("SELECT new dev.Daniel.Hospital_20.DTO.BedSpecialtyQtdDTO" +
			"(w.specialty,count(b))" +
			" FROM Bed b " +
			"JOIN b.room r " +
			"JOIN r.ward w WHERE w.hospital.id = :hospitalId " +
			"GROUP BY w.specialty")
	public List<BedSpecialtyQtdDTO> quantityPerWard(Long hospitalId);

	@Query("SELECT b " +
			" FROM Bed b " +
			"JOIN b.room r " +
			"JOIN r.ward w WHERE b.status = :status " +
			"and w.hospital.id = :hospitalId " +
			"and b.room.ward.specialty = :specialty")
	public List<Bed> bedSpecialty(Status status, Long hospitalId, Specialty specialty);


	@Query("select max(b.bedNumber) from Bed b where b.room.id = :roomId")
	public Long getBedNumber(Long roomId);


}
