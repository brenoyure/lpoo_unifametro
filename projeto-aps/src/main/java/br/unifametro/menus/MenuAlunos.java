package br.unifametro.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.unifametro.services.AlunoService;

public final class MenuAlunos {

	private final AlunoService servico;

	private boolean ficarNesteMenu = true;

	public MenuAlunos() {
		this.servico = new AlunoService();
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

			case 2:
				servico.editar(sc);
				break;

			case 3:
				servico.get(sc).ifPresentOrElse(a -> System.out.printf("\n%s\n", a),
						() -> System.err.printf("\nAluno com o ID informado não encontrado.\n"));
				break;

			case 4:
				System.out.println("DICA: Começando com letra maiúscula, ");
				servico.getByName(sc).forEach(System.out::println);
				break;

			case 5:
				servico.listar();
				break;

			case 6:
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
		System.out.printf("\n####### Menu de Alunos #########\n");

		System.out.printf("\n 1 - Cadastrar Novo Aluno");
		System.out.printf("\n 2 - Editar cadastro de Aluno");
		System.out.printf("\n 3 - Pesquisar um aluno por ID");
		System.out.printf("\n 4 - Pesquisa por Nome ou sobrenome");
		System.out.printf("\n 5 - Listar Alunos");
		System.out.printf("\n 6 - Excluir um aluno, por ID");
		System.out.printf("\n 0 - Sair");

		System.out.printf("\nEscolha uma opção => ");
	}

}
