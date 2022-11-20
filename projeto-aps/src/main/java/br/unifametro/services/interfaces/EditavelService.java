package br.unifametro.services.interfaces;

import java.util.Scanner;

/**
 * <p>Representa as funcionalidades que os Serviços de cada {@code Model} (ex. {@code Aluno} devem implementar.</p>
 * 
 * <br>
 * <p>Os Menus deverão chamar os métodos desta interface, e não as {@code Dao<T>} ou {@code DaoEditavel<T>} diretamente.</p>
 * 
 * <p>Esta Interface extende a {@code Service<T>}, ao fornecer a funcionalidade de editar um {@code T}.</p>
 * @param <T>
 */
public interface EditavelService<T> extends Service<T> {

    /**
     * Edita um {@value T}
     * <br>
	 * Dados são fornecidos via {@code Scanner} e repassados à {@code Dao} para persistência em arquivo.
     * @param scanner entrada via teclado.
     */
    void editar(Scanner scanner);

}
