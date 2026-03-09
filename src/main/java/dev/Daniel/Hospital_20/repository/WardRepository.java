package dev.Daniel.Hospital_20.repository;

import dev.Daniel.Hospital_20.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WardRepository extends JpaRepository<Ward,Long> {
}
