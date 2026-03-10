package dev.Daniel.Hospital_20.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Event {
	ADMISSION(1),
	DISCHARGE(2),
	INVALIDA(26);

	private final int valor;

	Event(int escolha){this.valor = escolha;}

	@JsonCreator
	public static Event deint(int opcoes){

		for (Event escolhas : values()){
			if (escolhas.valor == opcoes){
				return escolhas;
			}
		}
		return INVALIDA;
	}
}
