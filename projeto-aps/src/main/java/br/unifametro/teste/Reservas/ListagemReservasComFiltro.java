package br.unifametro.teste.Reservas;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.interfaces.auxiliares.BuscaBasicaService;

public class ListagemReservasComFiltro {

	public static void main(String[] args) {

		Dao<Aluno> alunosDao = new AlunoDao();
		BuscaBasicaService<Aluno> alunosService = new AlunoService(alunosDao, null);
		Dao<Reserva> dao = new ReservaDao(alunosService);

		dao.findAll().forEach(System.out::println);
		
		
	}

}
