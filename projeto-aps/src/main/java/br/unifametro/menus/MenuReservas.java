package br.unifametro.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifametro.modelo.Reserva;
import br.unifametro.services.interfaces.Service;

@Component
public final class MenuReservas {

	private final Service<Reserva> servico;
	private boolean ficarNesteMenu = true;

	@Autowired
	public MenuReservas(Service<Reserva> servico) {
		this.servico = servico;
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
			System.err.printf("\nVocê digitou '%s', apenas números são permitidos.\n", sc.next());
			exibirMenu(sc);
		}

		switch (opcao) {
		case 1:
			servico.cadastrar(sc);
			break;

		case 2:
			servico.get(sc).ifPresentOrElse(System.out::println,
					() -> System.err.printf("\nNenhuma Reserva com o ID do Aluno informado encontrada.\n"));
			break;

		case 3:
			servico.listar();
			break;

		case 4:
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
		System.out.printf("\n 2 - Pesquisar Reserva por ID do Aluno.");
		System.out.printf("\n 3 - Listar Reservas.");
		System.out.printf("\n 4 - Excluir Reservas de um Aluno, por ID.");
		System.out.printf("\n 0 - Sair.");

		System.out.printf("\nEscolha uma opção => ");
	}

}
