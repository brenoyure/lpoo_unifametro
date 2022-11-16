package br.unifametro.services.interfaces;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public interface Service<T> {

	void cadastrar(Scanner scanner);

	void excluir(Scanner scanner);

	Optional<T> get(Scanner scanner);

	Stream<T> getAll();

	void listar();

	boolean fileNotExists();

}
