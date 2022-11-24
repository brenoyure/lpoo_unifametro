package br.unifametro.persistencia.interfaces;

/**
 * <p>Data Access Object Interface</p>
 * 
 * <p>Representa o contexto de persistência como um arquivo TXT.</p>
 * <p>Os Menus <strong>NÃO</strong> devem invocar os métodos das interfaces Daos diretamente, e sim, utilizar as Services.</p>
 * 
 * <p>Esta Interface não possui o método de editar, visto que alguns {@code tipos <T>} podem não permitir esta funcionalidade.</p>
 * 
 * <p>Para edição, implemente a sub-interface {@code DaoEditavel<T>}</p>
 * @param <T> o tipo que será persistido.
 */

public interface DaoTXT<T> extends Dao<T> {

	/**
	 * Algumas Daos manipulam arquivos em que o nome varia, exemplo, baseado na data.
	 * A {@code String} que este método retorna pode ser passado no construtor de um {@code new File} sem problemas.
	 * @return Nome do arquivo de persistência.
	 */
	String getFileName();

	/**
	 * <p>Condição para o arquivo existir.</p>
	 * <p>Necessária para métodos que precisam buscar no arquivo, exemplo findAll, ou excluir.</p>
	 * @return Verdadeiro, por exemplo caso o arquivo exista e seu {@code length} seja maior que ZERO.
	 */
	default boolean fileExists() {
		//TODO ajeitar a implementação default
		//Nome deve ficar isEmpty e retornar Stream count == 0.
		return findAll().count() > 0;
	}
	
}
