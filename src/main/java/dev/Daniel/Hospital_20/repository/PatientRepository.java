package dev.Daniel.Hospital_20.repository;

import dev.Daniel.Hospital_20.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
