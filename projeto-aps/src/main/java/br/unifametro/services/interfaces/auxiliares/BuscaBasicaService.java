package br.unifametro.services.interfaces.auxiliares;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

public interface BuscaBasicaService<T> {

	Stream<T> getByName(Scanner scanner);
	Optional<T> getById(Integer id);
	
}
