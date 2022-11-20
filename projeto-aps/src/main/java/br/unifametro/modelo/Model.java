package br.unifametro.modelo;

/**
 * Representa as entidades do sistema
 * @author breno
 *
 */
public interface Model {

	/**
	 * String utilizada para representar um cadastro, por exemplo de um aluno, em arquivo.
	 * @return representação de um cadastro em arquivo.
	 */
	String toFile();

}
