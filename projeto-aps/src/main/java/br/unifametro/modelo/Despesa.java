package br.unifametro.modelo;

import java.math.BigDecimal;
import java.util.Objects;

public class Despesa implements Model {

	private String nome;

	private String descricao;

	private String categoria;

	private Prioridade PRIORIDADE;

	private BigDecimal valor;

	public Despesa(String nome, String descricao, String categoria, Prioridade pRIORIDADE, BigDecimal valor) {
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
		this.PRIORIDADE = pRIORIDADE;
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Despesa [nome=" + nome + ", descricao=" + descricao + ", categoria=" + categoria + ", PRIORIDADE="
				+ PRIORIDADE + ", valor=" + valor + "]";
	}

	public String toFile() {
		return String.format("%s ; %s ; %s ; %s ; %s", nome, descricao, categoria, PRIORIDADE, valor);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Despesa)) {
			return false;
		}
		Despesa other = (Despesa) obj;
		return Objects.equals(nome, other.nome);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public Prioridade getPRIORIDADE() {
		return PRIORIDADE;
	}

	public void setPRIORIDADE(Prioridade pRIORIDADE) {
		PRIORIDADE = pRIORIDADE;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

}
