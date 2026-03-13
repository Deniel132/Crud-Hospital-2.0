package dev.Daniel.Hospital_20.repository;

import dev.Daniel.Hospital_20.DTO.Bed_specialty_qt_DTO;
import dev.Daniel.Hospital_20.DTO.Quantity_bedsDTO;
import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import dev.Daniel.Hospital_20.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BedRepository extends JpaRepository<Bed, Long> {


	@Query("SELECT new dev.Daniel.Hospital_20.DTO.Quantity_bedsDTO( " +
			"COUNT (CASE WHEN b.status = dev.Daniel.Hospital_20.model.enums.Status.UNOCCUPIED THEN 1 ELSE NULL END), " +
			"COUNT (CASE WHEN b.status = dev.Daniel.Hospital_20.model.enums.Status.OCCUPIED  THEN 1 ELSE NULL END)) " +
			"FROM Bed b " +
			"WHERE b.room.ward.hospital.id = :hospital_id ")

	public Quantity_bedsDTO quantidade(Long hospital_id);

	@Query("SELECT new dev.Daniel.Hospital_20.DTO.Bed_specialty_qt_DTO" +
			"(w.specialty,count(b))" +
			" FROM Bed b " +
			"JOIN b.room r " +
			"JOIN r.ward w WHERE w.hospital.id = :hospital_id " +
			"GROUP BY w.specialty")
	public List<Bed_specialty_qt_DTO> qunatidadePorAla(Long hospital_id);

	@Query("SELECT b " +
			" FROM Bed b " +
			"JOIN b.room r " +
			"JOIN r.ward w WHERE b.status = :status " +
			"and w.hospital.id = :hospital_id " +
			"and b.room.ward.specialty = :specialty")
	public List<Bed> leitos_speciality(Status status, Long hospital_id, Specialty specialty);


}
