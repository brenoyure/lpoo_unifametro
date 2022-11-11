package br.unifametro.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.Dao;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.ReservasService;
import br.unifametro.services.Service;

public class MenuReservas {

	private Dao<Aluno> alunoDao = new AlunoDao();
	private Service<Aluno> alunoServices = new AlunoService(alunoDao);
	private Dao<Reserva> reservaDao = new ReservaDao((AlunoService) alunoServices);
	private Service<Reserva> servico = new ReservasService(reservaDao, (AlunoService) alunoServices);
	
	private Integer opcao = 0;

	public void exibirMenu(Scanner sc) {

		exibirOpcoes();

		try {
			opcao = sc.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("\nApenas números são permitidos\n");
			sc.nextLine();
			sc.reset();
			exibirMenu(sc);
		}
		sc.nextLine();

		switch (opcao) {
			case 1:
				servico.cadastrar(sc);
				exibirMenu(sc);
				break;

			case 2:
				servico.editar(sc);
				exibirMenu(sc);
				break;

			case 3:
				servico.get(sc).ifPresentOrElse(System.out::println,
						() -> System.err.println("Nenhuma Reserva com o ID do Aluno informado não encontrada."));
				servico.listar();
				exibirMenu(sc);
				break;

			case 4:
				servico.listar();
				exibirMenu(sc);
				break;

			case 5:
				servico.excluir(sc);
				exibirMenu(sc);
				break;

			case 0:
				break;

			default:
				System.err.println("Opção não identificada. Tente novamente.");
				exibirMenu(sc);
				break;
		}

	}

	private void exibirOpcoes() {
		System.out.println("\n####### Menu de Alunos #########");

		System.out.printf("\n 1 - Cadastrar Nova Reserva.");
		System.out.printf("\n 2 - Editar Reserva cadastrada.");
		System.out.printf("\n 3 - Pesquisar Reserva por ID do Aluno.");
		System.out.printf("\n 4 - Listar Reservas.");
		System.out.printf("\n 5 - Excluir Reservas de um Aluno, por ID.");
		System.out.printf("\n 0 - Sair.");

		System.out.printf("\nEscolha uma opção => ");
	}

}
