package br.unifametro.teste.Reservas;

import java.util.Scanner;

import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.ReservasService;
import br.unifametro.services.interfaces.Service;

public class CadastroReserva {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		AlunoService alunoServices = new AlunoService(new AlunoDao());
		Dao<Reserva> reservasDao = new ReservaDao(alunoServices);
		Service<Reserva> service = new ReservasService(reservasDao, alunoServices);
		service.cadastrar(sc);

	}

}
