package br.unifametro.services.interfaces.auxiliares;

/**
 * Validação básica dos dados de um {@code <T>}.
 * 
 * Esta Interface pode servir de auxilio as de {@code PreencheDados}
 * 
 * @author breno
 * @param <T>
 */
public interface ValidacaoDados<T> {

    /**
     * <p>
     * Valida e retorna um {@code <T>} para que os
     * Services enviem para as Daos para persistência.
     * </p>
     * 
     * <p>
     * Retorna um Null caso algum atributo não seja válido.
     * </p>
     */

    T validar(T t);

}
