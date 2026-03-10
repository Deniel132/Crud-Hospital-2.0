package dev.Daniel.Hospital_20.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Status {
	UNOCCUPIED(1),
	OCCUPIED(2),
	IN_PREPARATION(3),
	INVALIDA(26);

	private final int valor;

	Status(int escolha){this.valor = escolha;}

	@JsonCreator
	public static Status deint(int opcoes){

		for (Status escolhas : values()){
			if (escolhas.valor == opcoes){
				return escolhas;
			}
		}
		return INVALIDA;
	}

}
