package br.unifametro.services.interfaces.auxiliares.validacoes;

import java.util.Optional;

/**
 * Validação de dados de um {@code <T>} editável.
 * 
 * Esta Interface pode servir de auxilio à {@code PreencheDadosEditaveis}.
 * 
 * @author breno
 * @param <T>
 */
public interface ValidacaoDadosEditaveis<T> extends ValidacaoDados<T> {

    /**
     * <p>
     * Valida os dados que serão editados e retorna um {@code <T>} para que os
     * Services enviem para as Daos para persistência.
     * </p>
     * 
     * <p>
     * Retorna um {@code Optional} vazio caso algum atributo não seja válido.
     * </p>
     * 
     * @param t
     * @return
     */
	Optional<T> validarEdicao(T t);

}
