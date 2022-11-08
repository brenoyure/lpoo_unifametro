package br.unifametro.teste.Reservas;

import java.util.Scanner;

import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.ReservasService;

public class CadastroReserva {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ReservaDao reservasDao = new ReservaDao();
		AlunoService alunoServices = new AlunoService(new AlunoDao());
		ReservasService service = new ReservasService(reservasDao, alunoServices);

		service.cadastrar(sc);
		
	}

}
