package dev.Daniel.Hospital_20.repository;

import dev.Daniel.Hospital_20.DTO.AllPatientHospitalizedDTO;
import dev.Daniel.Hospital_20.DTO.BedHistoryDTO;
import dev.Daniel.Hospital_20.DTO.HistoryDTO;
import dev.Daniel.Hospital_20.DTO.PatientHospitalized;
import dev.Daniel.Hospital_20.model.AdmissionLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdmissionLogRepository extends JpaRepository<AdmissionLog, Long> {


	@Query("select new dev.Daniel.Hospital_20.DTO.PatientHospitalized(" +
			"a.bed.room.ward.hospital.name," +
			"a.bed.room.ward.specialty," +
			"a.bed.room.roomCode," +
			"a.patient.name," +
			"a.timeStamp)" +
			"from AdmissionLog a " +
			"join a.patient p where p.isHospitalized = true and p.id = :id " +
			"ORDER BY p.id asc " +
			"LIMIT 1 ")
	public PatientHospitalized patientInfo(Long id);


	@Query("select new dev.Daniel.Hospital_20.DTO.HistoryDTO(a.bed.room.ward.specialty,a.timeStamp," +
			"(SELECT min (lodDischarge.timeStamp) from AdmissionLog lodDischarge where lodDischarge.patient.id = a.patient.id " +
			"AND lodDischarge.eventType = dev.Daniel.Hospital_20.model.enums.Event.DISCHARGE " +
			"and lodDischarge.timeStamp > a.timeStamp)) " +
			"from AdmissionLog a " +
			"where  a.patient.id = :id " +
			"AND a.eventType = dev.Daniel.Hospital_20.model.enums.Event.ADMISSION ")
	public Page<HistoryDTO> history(Long id, Pageable pageable);


	@Query("SELECT new dev.Daniel.Hospital_20.DTO.AllPatientHospitalizedDTO(" +
			"a.patient.name, a.bed.room.ward.specialty, a.timeStamp) " +
			"FROM AdmissionLog a " +
			"JOIN a.patient p " +
			"WHERE p.isHospitalized = true " +
			"AND a.eventType = dev.Daniel.Hospital_20.model.enums.Event.ADMISSION " +
			"AND a.id = (" +
			"    SELECT max(al.id) " +
			"    FROM AdmissionLog al " +
			"    WHERE al.patient.id = a.patient.id " +
			"      AND al.eventType = dev.Daniel.Hospital_20.model.enums.Event.ADMISSION" +
			") " +
			"ORDER BY a.timeStamp DESC")
	public List<AllPatientHospitalizedDTO> All_hospitalized();


	@Query("select new dev.Daniel.Hospital_20.DTO.BedHistoryDTO(a.patient.name,a.bed.id,a.timeStamp," +
			"(SELECT min (lodDischarge.timeStamp) from AdmissionLog lodDischarge where lodDischarge.bed.id= a.bed.id " +
			"AND lodDischarge.eventType = dev.Daniel.Hospital_20.model.enums.Event.DISCHARGE " +
			"and lodDischarge.timeStamp > a.timeStamp)) " +
			"from AdmissionLog a " +
			"where  a.bed.id = :id " +
			"AND a.eventType = dev.Daniel.Hospital_20.model.enums.Event.ADMISSION ")
	public List<BedHistoryDTO> bedHistory(Long id);


}
