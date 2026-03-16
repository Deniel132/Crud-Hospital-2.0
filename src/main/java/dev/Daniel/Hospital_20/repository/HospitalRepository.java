package dev.Daniel.Hospital_20.repository;

import dev.Daniel.Hospital_20.model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Long> {

	boolean existsByCnpj(String cnpj);

	@Override
	boolean existsById(Long id);
}
