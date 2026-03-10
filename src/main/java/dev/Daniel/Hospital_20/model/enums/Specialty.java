package dev.Daniel.Hospital_20.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Specialty {
	CARDIOLOGY(1),
	DERMATOLOGY(2),
	GINECOLOGY(3),
	OBSTETRICS(4),
	PEDIATRICS(5),
	PSYCHIATRY(6),
	ORTHOPEDICS(7),
	TRAUMATOLOGY(8),
	OPHTHALMOLOGY(9),
	NEUROLOGY(10),
	ONCOLOGY(11),
	PEDIATRIC_CARDIOLOGY(12),
	ENDOCRINOLOGY(13),
	GASTROENTEROLOGY(14),
	HEMATOLOGY(15),
	INFECTIOUS_DISEASES(16),
	NEPHROLOGY(17),
	PNEUMOLOGY(18),
	UROLOGY(19),
	RADIOLOGY(20),
	ANESTHESIOLOGY(21),
	GENERAL_SURGERY(22),
	PLASTIC_SURGERY(23),
	INTERNAL_MEDICINE(24),
	GERIATRICS(25),
	INVALIDA(26);

	private final int valor;

	Specialty(int escolha){this.valor = escolha;}

	@JsonCreator
	public static Specialty deint(int opcoes){

		for (Specialty escolhas : values()){
			if (escolhas.valor == opcoes){
				return escolhas;
			}
		}
		return INVALIDA;
	}

}
