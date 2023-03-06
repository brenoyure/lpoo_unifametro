package br.unifametro.teste.Reservas;

import java.math.BigDecimal;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.interfaces.auxiliares.BuscaBasicaService;

public class TesteSomaReservas {

	public static void main(String[] args) {

		Dao<Aluno> alunoDao = new AlunoDao();
		BuscaBasicaService<Aluno> buscaService = new AlunoService(alunoDao, null);
		Dao<Reserva> reservasDao = new ReservaDao(buscaService);

		long qtdContribuicoes = reservasDao.findAll().filter(r -> r.getAluno().getId() == 222).count();

		BigDecimal totalDeContribuicoes = reservasDao.findAll().filter(r -> r.getAluno().getId() == 222)
				.map(r -> r.getValorDaContribuicao().multiply(BigDecimal.valueOf(qtdContribuicoes))).findFirst().get();

		System.out.printf("Contribuição Mensal foi de R$%s", totalDeContribuicoes);

	}

}
