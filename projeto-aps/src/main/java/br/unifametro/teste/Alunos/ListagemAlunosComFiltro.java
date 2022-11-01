package br.unifametro.teste.Alunos;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.function.Predicate;
import java.util.stream.Stream;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;

public class ListagemAlunosComFiltro {

	public static void main(String[] args) throws IOException {

		AlunoDao dao = new AlunoDao();
		Stream<Aluno> alunos = dao.findAll();

		BigDecimal comparador = new BigDecimal("2000");
		Predicate<Aluno> maiorQue = a -> a.getTotalDeRendimentos().compareTo(comparador) == 1;

		alunos.filter(maiorQue).forEach(a -> System.out.println(a.toFile()));

	}

}
