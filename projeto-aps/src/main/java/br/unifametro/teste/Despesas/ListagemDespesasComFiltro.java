package br.unifametro.teste.Despesas;

import java.math.BigDecimal;
import java.util.function.Predicate;
import java.util.stream.Stream;

import br.unifametro.modelo.Despesa;
import br.unifametro.modelo.Prioridade;
import br.unifametro.persistencia.DespesasDao;

public class ListagemDespesasComFiltro {

	public static void main(String[] args) {

		DespesasDao dao = new DespesasDao();
		Stream<Despesa> despesas = dao.findAll();
		
		BigDecimal valComp = new BigDecimal("100.00");
		Predicate<Despesa> condicao = d -> (d.getValor().compareTo(valComp) >= 0)
				&& (d.getPRIORIDADE().compareTo(Prioridade.ALTA) >= 0);

//		despesas.filter(condicao).findAny().ifPresentOrElse(System.out::println, () -> System.out.println("Nenhuma despesa encontrada."));
		
		despesas.filter(condicao).forEach(System.out::println);

	}

}
