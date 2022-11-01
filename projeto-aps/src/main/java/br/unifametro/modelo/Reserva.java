package br.unifametro.modelo;

import java.math.BigDecimal;

@SuppressWarnings("unused")
public class Reserva {

	private Aluno aluno;
	
	private BigDecimal totalDeRendimentos;
	
	private BigDecimal valorDaContribuicao;

	public Reserva(Aluno aluno, BigDecimal totalDeRendimentos, BigDecimal valorDaContribuicao) {
		this.aluno = aluno;
		this.totalDeRendimentos = totalDeRendimentos;
		this.valorDaContribuicao = valorDaContribuicao;
	}
	
	
	
	
}
