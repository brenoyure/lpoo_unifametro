package br.unifametro.teste.Despesas;

import java.math.BigDecimal;

import br.unifametro.modelo.Despesa;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.DespesasDao;

public class DivisaoDespesas {

    public static void main(String[] args) {

        Long qtdAlunos = new AlunoDao().findAll().count();
        Double totalDespesas = new DespesasDao().findAll().map(Despesa::getValor).mapToDouble(BigDecimal::doubleValue).sum();

        System.out.println(totalDespesas / qtdAlunos);

    }

}
