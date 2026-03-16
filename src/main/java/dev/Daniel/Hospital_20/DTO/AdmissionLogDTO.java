package dev.Daniel.Hospital_20.DTO;


import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AdmissionLogDTO {

	private Long bedId;
	private Long patientId;

	private LocalDateTime timeStamp;
}
