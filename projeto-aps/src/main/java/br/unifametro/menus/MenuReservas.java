package br.unifametro.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.ReservasService;
import br.unifametro.services.interfaces.Service;

public final class MenuReservas {

	private final Service<Aluno> alunoServices;
	private final Dao<Reserva> reservaDao;
	private final Service<Reserva> servico;

	private boolean ficarNesteMenu = true;

	public MenuReservas() {
		this.alunoServices = new AlunoService();
		this.reservaDao = new ReservaDao(alunoServices);
		this.servico = new ReservasService(reservaDao, alunoServices);
	}

	public void exibirMenu(Scanner sc) {

		do {
			exibir(sc);
		} while (ficarNesteMenu == true);
	}

	private void exibir(Scanner sc) {

		exibirOpcoes();
		int opcao = 0;
		ficarNesteMenu = true;

		try {
			opcao = sc.nextInt();
		} catch (InputMismatchException e) {
			System.err.printf("Você digitou '%s', apenas números são permitidos.", sc.next());
			exibirMenu(sc);
		}

		switch (opcao) {
			case 1:
				servico.cadastrar(sc);
				break;

			case 3:
				servico.get(sc).ifPresentOrElse(System.out::println,
						() -> System.err.println("Nenhuma Reserva com o ID do Aluno informado encontrada."));
				break;

			case 4:
				servico.listar();
				break;

			case 5:
				servico.excluir(sc);
				break;

			case 0:
				ficarNesteMenu = false;
				break;

			default:
				System.err.println("Opção não identificada. Tente novamente.");
				break;
		}

	}

	private void exibirOpcoes() {
		System.out.println("\n####### Menu de Reservas #########");

		System.out.printf("\n 1 - Cadastrar Nova Reserva.");
		System.out.printf("\n 2 - Editar Reserva cadastrada.");
		System.out.printf("\n 3 - Pesquisar Reserva por ID do Aluno.");
		System.out.printf("\n 4 - Listar Reservas.");
		System.out.printf("\n 5 - Excluir Reservas de um Aluno, por ID.");
		System.out.printf("\n 0 - Sair.");

		System.out.printf("\nEscolha uma opção => ");
	}

}
