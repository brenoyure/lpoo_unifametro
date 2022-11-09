package br.unifametro.teste.Reservas;

import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.ReservasService;

public class ListagemReservas {

	public static void main(String[] args) {

		AlunoService alunoServices = new AlunoService(new AlunoDao());
		ReservaDao dao = new ReservaDao(alunoServices);
		ReservasService service = new ReservasService(dao, alunoServices);

		service.listar();

	}

}
