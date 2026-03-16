package dev.Daniel.Hospital_20.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuantityBedsDTO {

	private Long bedsUNOCCUPIED;
	private Long bedsOCCUPIED;
	private Long bedsINPREPARATION;

	public QuantityBedsDTO(Long bedsUNOCCUPIED, Long bedsOCCUPIED, Long bedsINPREPARATION) {
		this.setBedsUNOCCUPIED(bedsUNOCCUPIED);
		this.setBedsOCCUPIED(bedsOCCUPIED);
		this.setBedsINPREPARATION(bedsINPREPARATION);
	}

	private List<BedSpecialtyQtdDTO> bedSpecialtyQtDto;
}
