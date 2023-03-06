package br.unifametro.teste.Reservas;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.interfaces.auxiliares.BuscaBasicaService;

public class ListagemReservas {

	public static void main(String[] args) {

		Dao<Aluno> alunoDao = new AlunoDao();
		BuscaBasicaService<Aluno> buscaService = new AlunoService(alunoDao, null);
		Dao<Reserva> dao = new ReservaDao(buscaService);
		dao.findAll().forEach(System.out::println);

	}

}
