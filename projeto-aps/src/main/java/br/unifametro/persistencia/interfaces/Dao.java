package br.unifametro.persistencia.interfaces;

import java.util.stream.Stream;

/**
 *<p>Representa o contexto de persistência, geralmente um arquivo '.txt' .</p>
 * <br>
 * <p>Os Menus <strong>NÃO</strong> devem invocar os métodos das interfaces Daos diretamente, e sim, utilizar as Services.</p>
 * 
 * <p>Esta Interface não possui o método de editar, visto que alguns {@code Model}s podem não permitir esta funcionalidade.</p>
 * 
 * <p>Para edição, implemente a sub-interface {@code DaoEditavel<T>}</p>
 * @param T o tipo que será persistido.
 */
public interface Dao<T> {

	/**
	 * Persiste um novo {@code t} no arquivo.
	 * @param t
	 */
	void salvar(T t);

	/**
	 * <p>Exclui um {@code <T>} existente.</p>
	 * <p>Visto que para excluir é necessário existir pelo menos um registro, 
	 * recomenda-se verificar antes se o arquivo existe, por exemplo com o {@code fileExists()} antes de invocar este método.</p>
	 * @param t
	 */
	void excluir(T t);

	/**
	 * Lê o arquivo, encontra e retorna todos os {@code <T>}.
	 *<p>Visto que é necessário existir pelo menos um registro para que este método funcione corretamente, 
	 * recomenda-se verificar antes se o arquivo existe, por exemplo com o {@code fileExists()}.</p>
	 * @return um {@code Stream<T>}
	 */
	Stream<T> findAll();

	/**
	 * Algumas Daos manipulam arquivos em que o nome varia, exemplo, baseado na data.
	 * A {@code String} que este método retorna pode ser passado no construtor de um {@code new File} sem problemas.
	 * @return nome do arquivo de persistência.
	 */
	String getFileName();

	/**
	 * Condição para o arquivo existir.
	 * <br>
	 * Necessária para métodos que precisam buscar no arquivo, exemplo findAll, ou excluir.
	 * <br>
	 * @return Verdadeiro, por exemplo caso o arquivo exista e seu {@code length} seja maior que ZERO.
	 */
	boolean fileExists();

}
