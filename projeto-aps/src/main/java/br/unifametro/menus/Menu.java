package br.unifametro.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

	private final Scanner sc;
	private MenuAlunos alunos;
	private MenuReservas reservas;
	private Integer opcao;

	public Menu(Scanner keyboardScanner) {
		this.sc = keyboardScanner;
		this.alunos = new MenuAlunos();
		this.reservas = new MenuReservas();
	}

	public void menuPrincipal() {

		do {
			exibir(sc);
		} while (opcao != 0);

	}

	public void exibir(Scanner scanner) {

		System.out.println("\n####### República Unifametro #########");
		System.out.println("");

		System.out.printf("\n 1 - Serviços de Alunos");
		System.out.printf("\n 2 - Serviços de Reservas");
		System.out.printf("\n 0 - Sair");

		System.out.printf("\nEscolha uma opção => ");
		try {
			opcao = sc.nextInt();
		} catch (InputMismatchException e) {
			System.err.println("\nApenas números são permitidos\n");
			sc.nextLine();
			sc.reset();
			exibir(sc);
		}
		sc.nextLine();

		switch (opcao) {
			case 1:
				alunos.exibirMenu(scanner);
				break;

			case 2:
				reservas.exibirMenu(scanner);
				break;

			case 0:
				opcao = 0;
				break;

			default:
				System.err.println("Opção não identificada, tente novamente.");
				break;
		}

	}

}
