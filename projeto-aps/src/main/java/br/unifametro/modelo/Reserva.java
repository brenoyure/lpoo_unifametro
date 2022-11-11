package br.unifametro.modelo;

import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;
import java.util.Objects;

public class Reserva implements Model {

	private Integer alunoId;

	private Aluno aluno;

	private BigDecimal totalDeRendimentos;

	private BigDecimal valorDaContribuicao;

	public Reserva() {

	}

	/**
	 * Construtor para o cadastro de nova reserva.
	 * 
	 * @param aluno
	 */
	public Reserva(Aluno aluno) {
		this.alunoId = aluno.getId();
		this.aluno = aluno;
		this.totalDeRendimentos = aluno.getTotalDeRendimentos();
		this.valorDaContribuicao = getValorDaContribuicao();
	}

	@Override
	public String toFile() {
		return String.format("%d ; %s ; %s ; %s", aluno.getId(), aluno.getNome(), totalDeRendimentos,
				valorDaContribuicao);
	}

	@Override
	public String toString() {
		return String.format("Reserva => [Aluno %s de ID: %d | Total de Rendimentos R$%s | Valor da Contribuição R$%s",
				aluno.getNome(), alunoId, totalDeRendimentos, valorDaContribuicao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(alunoId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Reserva)) {
			return false;
		}
		Reserva other = (Reserva) obj;
		return Objects.equals(alunoId, other.alunoId);
	}

	public Integer getAlunoId() {
		return alunoId;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public BigDecimal getTotalDeRendimentos() {
		return totalDeRendimentos;
	}

	public void setTotalDeRendimentos(BigDecimal totalDeRendimentos) {
		this.totalDeRendimentos = totalDeRendimentos;
	}

	public BigDecimal getValorDaContribuicao() {
		return valorDaContribuicao = totalDeRendimentos.multiply(new BigDecimal(0.05)).setScale(2, HALF_UP);
	}

	public void setValorDaContribuicao(BigDecimal valorDaContribuicao) {
		this.valorDaContribuicao = valorDaContribuicao;
	}

}
