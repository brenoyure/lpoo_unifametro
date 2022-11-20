package br.unifametro.persistencia.interfaces;

/**
 * <p>
 * Representa o contexto de persistência, geralmente um arquivo '.txt' .
 * </p>
 * 
 * <p>
 * <strong>OBS: </strong>Os Menus <strong>NÃO</strong> devem invocar os métodos
 * das interfaces Daos diretamente, e sim, utilizar as Services.
 * </p>
 * 
 * <p>
 * Esta Interface extende a {@code Dao<T>}, ao fornecer a funcionalidade de
 * editar um {@code <T>}.
 * </p>
 * 
 * @param <T> o tipo que será persistido.
 */
public interface DaoEditavel<T> extends Dao<T> {

    /**
     * Edita um registro no arquivo de persistência, geralmente substiuindo a linha
     * dos dados antigos pelos novos.
     * 
     * @param dadosAntigos
     * @param dadosNovos
     */
    void editar(T dadosAntigos, T dadosNovos);

}
