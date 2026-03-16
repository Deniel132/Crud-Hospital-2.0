package dev.Daniel.Hospital_20.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BedHistoryDTO {

	private String PatientName;

	private Long bedId;

	private LocalDateTime dateAdmission;

	private LocalDateTime dateDischarge;

}
