package br.unifametro.services.auxiliares;

import static java.lang.String.format;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;

import java.math.BigDecimal;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Despesa;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.DespesasDao;
import br.unifametro.persistencia.interfaces.Dao;

/**
 * {@code Service} responsável por calcular quanto cada aluno deve contribuir para as
 * Despesas do mês.
 */
public final class DivisaoDespesas {

    private Dao<Aluno> alunosDao;
    private Dao<Despesa> despesasDao;

    public DivisaoDespesas() {
        this.alunosDao = new AlunoDao();
        this.despesasDao = new DespesasDao();
    }

    /**
     * Calcula quanto cada um deve contribuir para cobrir as despesas do mês.
     * 
     * @return BigDecimal quanto cada aluno deve contribuir para as Despesas do mês.
     */
    public BigDecimal getDivisaoIgualitaria() {
        return getValorTotalDespesas().divide(valueOf(getQuantidadeAlunos()), HALF_UP);
    }

    public String getResumoDivisao() {
        return format(
                "Para cobrir as despesas do mês, cada um dos %d alunos, deve contribuir com R$%s, de seus rendimentos.",
                getQuantidadeAlunos(), getDivisaoIgualitaria());
    }

    /**
     * 
     * @return Quantidade de Alunos da República.
     */
    public Long getQuantidadeAlunos() {
        return this.alunosDao.findAll().count();
    }

    /**
     * 
     * @return Quantidade de Despesas.
     */
    public Long getQuantidadeDespesas() {
    	return this.despesasDao.findAll().count();
    }

    /**
     * Soma os valores de todas as despesas do mês e devolve um BigDecimal.
     * <br>
     * @return Valor total de Despesas do mês.
     */
    public BigDecimal getValorTotalDespesas() {
        return valueOf(despesasDao.findAll().map(Despesa::getValor).mapToDouble(BigDecimal::doubleValue).sum()).setScale(2, HALF_UP);
    }

}
