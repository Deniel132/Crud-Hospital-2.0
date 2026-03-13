package dev.Daniel.Hospital_20.repository;

import dev.Daniel.Hospital_20.model.Ward;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WardRepository extends JpaRepository<Ward, Long> {


	@Query("SELECT w from Ward w where w.specialty = :specialty and w.hospital.id = :hospital_id")
	public Ward especialidade_igual(Specialty specialty, Long hospital_id);


	@Query("select w.specialty from Ward w WHERE w.hospital.id = :hospital_id ORDER BY w.specialty desc ")
	public List<Specialty> lista_specialty(Long hospital_id);


}
