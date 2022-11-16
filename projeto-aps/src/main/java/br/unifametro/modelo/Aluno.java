package br.unifametro.modelo;

import java.math.BigDecimal;
import java.util.Locale;

public class Aluno implements Model {

	private Integer id;
	private String nome;
	private String email;
	private BigDecimal totalDeRendimentos;

	/**
	 * Construtor vazio, talvez seja útil, who knows...
	 */
	public Aluno() {

	}

	/**
	 * Construtor sem ID, geralmente usado para editar um aluno.
	 * 
	 * @see br.unifametro.services.AlunoService <- Classe AlunoService
	 * @param nome
	 * @param email
	 * @param totalDeRendimentos
	 */
	public Aluno(String nome, String email, BigDecimal totalDeRendimentos) {
		this.nome = nome;
		this.email = email;
		this.totalDeRendimentos = totalDeRendimentos;
	}

	/**
	 * Construtor com ID, utilizado para cadastro de novo Aluno.
	 * 
	 * @see br.unifametro.services.AlunoService <- Classe AlunoService
	 * @param id
	 * @param nome
	 * @param email
	 * @param totalDeRendimentos
	 */
	public Aluno(Integer id, String nome, String email, BigDecimal totalDeRendimentos) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.totalDeRendimentos = totalDeRendimentos;
	}

	@Override
	public String toString() {
		return String.format(new Locale("PT", "BR"),
				"Aluno => [ID: %d | Nome: %s | Email: %s | Total de Rendimentos: R$%s]", id, nome, email,
				totalDeRendimentos);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	/**
	 * String utilizada para representar o cadastro do tipo Aluno em um arquivo.
	 * 
	 * @see br.unifametro.services.AlunoService <- Classe AlunoService
	 * @return representação em String do Aluno para o txt. Como se fosse um JSON
	 *         ... só que não
	 */
	public String toFile() {
		return String.format("%d ; %s ; %s ; %s", id, nome, email, totalDeRendimentos);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getTotalDeRendimentos() {
		return totalDeRendimentos;
	}

	public void setTotalDeRendimentos(BigDecimal totalDeRendimentos) {
		this.totalDeRendimentos = totalDeRendimentos;
	}

}
