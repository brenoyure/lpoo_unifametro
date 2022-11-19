package br.unifametro.services.interfaces;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * <p>Representa as funcionalidades básicas que os Serviços de cada {@code Model} (ex. {@code Aluno} deve implementar.<br>
 * Os Menus deverão chamar os métodos desta interface, e não das {@code Dao<T>} diretamente.</p>
 * <p>Para edição, implemente a sub-interface {@code EditavelService<T>}  </p>
 * @author breno
 * @param <T>
 */
public interface Service<T> {

	/**
	 * Cadastra um {@code Model}<br>
	 * Dados são fornecidos via {@code Scanner} e repassados à {@code Dao} para persistência em arquivo.
	 * @param scanner do tipo {@code System.in}
	 */
	void cadastrar(Scanner scanner);

	/**
	 * Exclui um {@code Model}<br>
	 * Dados são fornecidos via {@code Scanner} e repassados à {@code Dao} para persistência em arquivo.
	 * @param scanner do tipo {@code System.in}
	 */
	void excluir(Scanner scanner);

	/**
	 * Retorna um {@code Optional<T>).
	 * @param scanner
	 * @return Optional<T>
	 */
	Optional<T> get(Scanner scanner);

	/**
	 * 
	 * @return Stream<T> contendo todos os registros da Dao<T>
	 */
	Stream<T> getAll();

	void listar();

	boolean fileNotExists();

}
