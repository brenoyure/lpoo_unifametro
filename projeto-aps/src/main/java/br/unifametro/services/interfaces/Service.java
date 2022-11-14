package br.unifametro.services.interfaces;

import java.util.Optional;
import java.util.Scanner;

public interface Service<T> {

	void cadastrar(Scanner scanner);

	void excluir(Scanner scanner);

	Optional<T> get(Scanner scanner);

	void listar();

	boolean fileNotExists();

}
