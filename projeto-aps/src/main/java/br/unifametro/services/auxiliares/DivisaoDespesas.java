package br.unifametro.services.auxiliares;

import static java.lang.String.format;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Despesa;
import br.unifametro.persistencia.interfaces.Dao;

/**
 * Responsável por calcular quanto cada aluno deve contribuir
 * para as Despesas do mês.
 */
@Service
public final class DivisaoDespesas {

	private Dao<Aluno> alunosDao;
	private Dao<Despesa> despesasDao;

	public DivisaoDespesas(Dao<Aluno> alunosDao, Dao<Despesa> despesasDao) {
		this.alunosDao = alunosDao;
		this.despesasDao = despesasDao;
	}

	/**
	 * Calcula quanto cada um deve contribuir para cobrir as despesas do mês.
	 * 
	 * @return quanto cada aluno deve contribuir para as despesas do mês.
	 */
	private BigDecimal getDivisaoIgualitaria() {
		return getValorTotalDespesas().divide(valueOf(getQuantidadeAlunos()), HALF_UP);
	}

	/**
	 * Imprime o resumo das despesas do mês.
	 */
	public void resumir() {
		
		if (despesasDao.findAll().count() == 0) {
			System.err.println("Nenhuma Despesa cadastrada no mês atual.");
			return;
		}

		System.out.printf("\n%s\n", getResumo());

	}

	private String getResumo() {

		StringBuilder sb = new StringBuilder();
		Long qtdDespesas = this.getQuantidadeDespesas();
		BigDecimal vTotal = this.getValorTotalDespesas();
		BigDecimal divisao = this.getDivisaoIgualitaria();

		sb.append("\n########### Resumo do Mês Atual ############\n");
		sb.append(format("\nTotal de Despesas: %d.\n", qtdDespesas));
		sb.append(format("\nValor total: R$%s.\n", vTotal));
		sb.append(format("\nCada aluno deve contribuir com: R$%s\n", divisao));
		sb.append("\n############################################\n");

		return sb.toString();

	}

	/**
	 * 
	 * @return Quantidade de Alunos da República.
	 */
	private Long getQuantidadeAlunos() {
		return this.alunosDao.findAll().count();
	}

	/**
	 * 
	 * @return Quantidade de Despesas.
	 */
	private Long getQuantidadeDespesas() {
		return this.despesasDao.findAll().count();
	}

	/**
	 * Soma os valores de todas as despesas do mês e devolve um BigDecimal. <br>
	 * 
	 * @return Valor total de Despesas do mês.
	 */
	private BigDecimal getValorTotalDespesas() {
		return valueOf(despesasDao.findAll().map(Despesa::getValor).mapToDouble(BigDecimal::doubleValue).sum())
				.setScale(2, HALF_UP);
	}

}
