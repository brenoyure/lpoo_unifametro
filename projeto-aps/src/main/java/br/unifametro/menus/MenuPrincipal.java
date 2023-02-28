package br.unifametro.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.unifametro.menus.interfaces.Menu;

@Component
public final class MenuPrincipal implements Menu {

	private final MenuAlunos alunos;
	private final MenuReservas reservas;
	private final MenuDespesas despesas;
	private boolean continuarNoAplicativo = true;

	@Autowired
	public MenuPrincipal(MenuAlunos alunos, MenuReservas reservas, MenuDespesas despesas) {
		this.alunos = alunos;
		this.reservas = reservas;
		this.despesas = despesas;
	}

	public void exibirMenu(Scanner scanner) {
		exibirDicas();
		do {
			exibirMenuPrincipal(scanner);
		} while (continuarNoAplicativo == true);

	}

	private void exibirMenuPrincipal(Scanner scanner) {

		exibirOpcoes();
		int opcao = 0;

		try {
			opcao = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.err.printf("Você digitou '%s', apenas números são permitidos ", scanner.next());
			exibirMenuPrincipal(scanner);
		}

		switch (opcao) {
			case 1:
				alunos.exibirMenu(scanner);
				break;

			case 2:
				reservas.exibirMenu(scanner);
				break;

			case 3:
				despesas.exibirMenu(scanner);
				break;

			case 0:
				continuarNoAplicativo = false;
				break;

			default:
				System.err.println("Opção não identificada, tente novamente.");
				break;
		}

	}

	private void exibirOpcoes() {
		System.out.printf("\n####### República Unifametro #########\n");

		System.out.printf("\n 1 - Serviços de Alunos");
		System.out.printf("\n 2 - Serviços de Reservas");
		System.out.printf("\n 3 - Serviços de Despesas");
		System.out.printf("\n 0 - Sair");

		System.out.printf("\nEscolha uma opção => ");
	}

	private void exibirDicas() {
		System.out.println("\nBoas vindas ao aplicativo República Unifametro");
		System.out.println(
				"DICAS para uma boa experiência ao cadastrar ou editar os registros");
		System.out.println(
				"\n => Campos texto, como Nome ou E-mail: Favor não utilizar o ; visto que este é utilizado ao salvar os dados no arquivo .txt");
		System.out.println(
				"\n => Valores monetários, separe o decimal por vírgula, por exemplo, utilize ' R$5,00 ' ao invés de ' R$ 5.00 '.");
	}

}
