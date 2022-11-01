package br.unifametro.modelo;

import java.math.BigDecimal;

public class Aluno {

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
		return "Aluno [id=" + id + ", nome=" + nome + ", email=" + email + ", totalDeRendimentos=" + totalDeRendimentos
				+ "]";
	}

	/**
	 * String utilizada para representar o cadastro do tipo Aluno em um arquivo. 
	 * @see br.unifametro.services.AlunoService <- Classe AlunoService
	 * @return representação em String do Aluno para o txt. Como se fosse um JSON ... só que não
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
