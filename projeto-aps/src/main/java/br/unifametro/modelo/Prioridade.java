package br.unifametro.modelo;

public enum Prioridade {

	PODE_ESPERAR(1), BAIXA(2), MEDIA(3), ALTA(4), URGENTE(5);

	Prioridade(int i) {
		i = ordinal();
	}

}
