package br.unifametro.services;

import java.util.Optional;
import java.util.Scanner;

public interface Service<T> {

	void cadastrar(Scanner scanner);

	void editar(Scanner scanner);

	void excluir(Scanner scanner);

	Optional<T> get(Scanner scanner);

	void listar();

	T getDados(Scanner scanner);

	boolean fileNotExists();

}
