package dev.Daniel.Hospital_20.DTO;

import dev.Daniel.Hospital_20.model.Bed;
import dev.Daniel.Hospital_20.model.enums.Specialty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Leitos_por_especialidadeDTO {


	private Specialty specialty;
	private List<Bed> beds;




}
