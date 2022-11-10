package br.unifametro.teste.Reservas;

import java.math.BigDecimal;

import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.services.AlunoService;

public class TesteSomaReservas {

	public static void main(String[] args) {

		AlunoService alunoServices = new AlunoService(new AlunoDao());
		ReservaDao reservasDao = new ReservaDao(alunoServices);

		long qtdContribuicoes = reservasDao.findAll().filter(r -> r.getAluno().getId() == 222).count();

		BigDecimal totalDeContribuicoes = reservasDao.findAll().filter(r -> r.getAluno().getId() == 222)
				.map(r -> r.getValorDaContribuicao().multiply(BigDecimal.valueOf(qtdContribuicoes))).findFirst().get();

		System.out.printf("Contribuição Mensal foi de R$%s", totalDeContribuicoes);

	}

}
