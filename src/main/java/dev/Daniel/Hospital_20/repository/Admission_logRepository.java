package dev.Daniel.Hospital_20.repository;

import dev.Daniel.Hospital_20.DTO.All_patient_hospitalized_DTO;
import dev.Daniel.Hospital_20.DTO.Historico_Bed_DTO;
import dev.Daniel.Hospital_20.DTO.Historico_DTO;
import dev.Daniel.Hospital_20.DTO.Patient_hospitalized;
import dev.Daniel.Hospital_20.model.Admission_log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface Admission_logRepository extends JpaRepository<Admission_log,Long> {


	@Query("select new dev.Daniel.Hospital_20.DTO.Patient_hospitalized(" +
			"a.bed.room.ward.hospital.nome," +
			"a.bed.room.ward.specialty," +
			"a.bed.room.room_code," +
			"a.patient.name," +
			"a.hora," +
			"a.date) " +
			"from Admission_log a " +
			"join a.patient p where p.is_hospitalized = true and p.id = :id " +
			"ORDER BY p.id asc " +
			"LIMIT 1 ")
	public Patient_hospitalized patienteInfo(Long id);


	@Query("select new dev.Daniel.Hospital_20.DTO.Historico_DTO(a.bed.room.ward.specialty,a.date," +
			"(SELECT min (logalta.date) from Admission_log logalta where logalta.patient.id = a.patient.id " +
			"AND logalta.event_type = dev.Daniel.Hospital_20.model.enums.Event.DISCHARGE " +
			"and logalta.date > a.date)) " +
			"from Admission_log a " +
			"where  a.patient.id = :id " +
			"AND a.event_type = dev.Daniel.Hospital_20.model.enums.Event.ADMISSION ")
	public Page<Historico_DTO> historico(Long id, Pageable pageable);



	@Query("SELECT new dev.Daniel.Hospital_20.DTO.All_patient_hospitalized_DTO(" +
			"a.patient.name, a.bed.room.ward.specialty, a.date) " +
			"FROM Admission_log a " +
			"JOIN a.patient p " +
			"WHERE p.is_hospitalized = true " +
			"AND a.event_type = dev.Daniel.Hospital_20.model.enums.Event.ADMISSION " +
			"AND a.id = (" +
			"    SELECT max(al.id) " +
			"    FROM Admission_log al " +
			"    WHERE al.patient.id = a.patient.id " +
			"      AND al.event_type = dev.Daniel.Hospital_20.model.enums.Event.ADMISSION" +
			") " +
			"ORDER BY a.date DESC")
	public List<All_patient_hospitalized_DTO> All_hospitalized();



	@Query("select new dev.Daniel.Hospital_20.DTO.Historico_Bed_DTO(a.patient.name,a.bed.id,a.date," +
			"(SELECT min (logalta.date) from Admission_log logalta where logalta.bed.id= a.bed.id " +
			"AND logalta.event_type = dev.Daniel.Hospital_20.model.enums.Event.DISCHARGE " +
			"and logalta.date > a.date)) " +
			"from Admission_log a " +
			"where  a.bed.id = :id " +
			"AND a.event_type = dev.Daniel.Hospital_20.model.enums.Event.ADMISSION ")
	public List<Historico_Bed_DTO> historico_Bed(Long id);


}
