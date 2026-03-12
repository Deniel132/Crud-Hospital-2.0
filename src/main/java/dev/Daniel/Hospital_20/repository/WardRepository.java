package dev.Daniel.Hospital_20.repository;

import dev.Daniel.Hospital_20.DTO.Beds_specialtyDTO;
import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import dev.Daniel.Hospital_20.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward,Long> {


	@Query("SELECT w from Ward w where w.specialty = :specialty and w.hospital.id = :hospital_id")
	public Ward especialidade_igual(Specialty specialty,Long hospital_id);


}
