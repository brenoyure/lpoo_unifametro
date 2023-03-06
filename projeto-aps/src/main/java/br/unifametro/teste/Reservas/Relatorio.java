package br.unifametro.teste.Reservas;

import static java.util.stream.Collectors.toList;

import java.util.List;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.auxiliares.AlunoPreencheDados;
import br.unifametro.services.auxiliares.AlunoValidaDados;
import br.unifametro.services.interfaces.auxiliares.BuscaBasicaService;
import br.unifametro.services.interfaces.auxiliares.PreencheDadosComEdicao;
import br.unifametro.services.interfaces.auxiliares.ValidacaoDadosEditaveis;

public class Relatorio {

	public static void main(String[] args) {

		Dao<Aluno> alunosDao = new AlunoDao();
		ValidacaoDadosEditaveis<Aluno> validacao = new AlunoValidaDados(alunosDao);
		PreencheDadosComEdicao<Aluno> getDados = new AlunoPreencheDados(validacao);
		BuscaBasicaService<Aluno> alunoService = new AlunoService(alunosDao, getDados);
		Dao<Reserva> reservasDao = new ReservaDao(alunoService);

		List<Aluno> alunos = alunosDao.findAll().collect(toList());
		List<Aluno> pagos = reservasDao.findAll().map(Reserva::getAluno).toList();

		alunos.removeAll(pagos);
		alunos.forEach(System.out::println);

	}

}
