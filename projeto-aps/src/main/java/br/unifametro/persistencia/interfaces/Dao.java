package br.unifametro.persistencia.interfaces;

import java.util.stream.Stream;

/**
 * <p>Data Access Object Interface</p>
 * 
 * <p>Representa o contexto de persistência.</p>
 * 
 * <p>Os Menus <strong>NÃO</strong> devem invocar os métodos das interfaces Daos diretamente, e sim, utilizar as Services.</p>
 * 
 * <p>Esta Interface não possui o método de editar, visto que alguns {@code tipos <T>} podem não permitir esta funcionalidade.</p>
 * 
 * <p>Para edição, implemente a sub-interface {@code DaoEditavel<T>}</p>
 * @param <T> o tipo que será persistido.
 */
public interface Dao<T> {

	/**
	 * Persiste um novo {@code t} no arquivo.
	 * @param <T>
	 */
	void salvar(T t);

	/**
	 * <p>Exclui um {@code <T>} existente.</p>
	 * <p>Visto que para excluir é necessário existir pelo menos um registro, 
	 * recomenda-se verificar antes se o arquivo existe, por exemplo com o {@code fileExists()} antes de invocar este método.</p>
	 * @param <T>
	 */
	void excluir(T t);

	/**
	 * Encontra e retorna todos os {@code <T>}.
	 * @return um {@code Stream<T>}
	 */
	Stream<T> findAll();

}
