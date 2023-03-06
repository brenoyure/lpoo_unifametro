package br.unifametro.services.auxiliares.exceptions.validacoes;

/**
 * Representa um erro de validação
 * @author breno.brito
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ValidationException(String message) {
        super(message);
    }
	
}
