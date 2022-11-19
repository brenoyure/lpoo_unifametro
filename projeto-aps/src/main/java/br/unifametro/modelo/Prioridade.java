package br.unifametro.modelo;

/**
 * Possui os níveis de {@code Prioridade} das {@code Despesa}s variadas.
 * @author breno
 *
 */
public enum Prioridade {

	PODE_ESPERAR(1), BAIXA(2), MEDIA(3), ALTA(4), URGENTE(5);

	Prioridade(int i) {
		i = ordinal();
	}

}
