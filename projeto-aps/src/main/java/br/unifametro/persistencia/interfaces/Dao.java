package br.unifametro.persistencia.interfaces;

import java.util.stream.Stream;

public interface Dao<T> {

	void salvar(T t);

	void excluir(T t);

	Stream<T> findAll();

	String getFileName();

	boolean fileExists();

}
