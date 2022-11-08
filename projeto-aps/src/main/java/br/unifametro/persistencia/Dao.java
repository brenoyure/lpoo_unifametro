package br.unifametro.persistencia;

import java.util.stream.Stream;

public interface Dao<T> {

	void salvar(T t);

	void editar(T dadosAntigos, T dadosNovos);

	void excluir(T t);

	Stream<T> findAll();

	String getFileName();

	boolean fileExists();

}
