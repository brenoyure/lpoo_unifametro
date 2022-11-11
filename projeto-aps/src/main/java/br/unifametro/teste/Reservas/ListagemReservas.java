package br.unifametro.teste.Reservas;

import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.Dao;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.ReservasService;
import br.unifametro.services.Service;

public class ListagemReservas {

	public static void main(String[] args) {

		AlunoService alunoServices = new AlunoService(new AlunoDao());
		Dao<Reserva> dao = new ReservaDao(alunoServices);
		Service<Reserva> service = new ReservasService(dao, alunoServices);

		service.listar();

	}

}
