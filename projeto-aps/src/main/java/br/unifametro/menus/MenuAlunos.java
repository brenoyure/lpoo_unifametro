package br.unifametro.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.Service;

public class MenuAlunos {

	private final Service<Aluno> servico;

	private boolean ficarNesteMenu = true;

	public MenuAlunos() {
		this.servico = new AlunoService(new AlunoDao());
	}

	public void exibirMenu(Scanner sc) {

		do {
			exibir(sc);
		} while (ficarNesteMenu == true);
	}

	private void exibir(Scanner sc) {
		exibirOpcoes();
		int opcao = 0;

		try {
			opcao = sc.nextInt();
		} catch (InputMismatchException e) {
			System.err.printf("Você digitou '%s', apenas números são permitidos ", sc.next());
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
				servico.get(sc).ifPresentOrElse(a -> System.out.printf("\n%s\n", a), () -> System.err.println("Aluno não encontrado"));
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
		System.out.printf("\n####### Menu de Alunos #########\n");

		System.out.printf("\n 1 - Cadastrar Novo Aluno");
		System.out.printf("\n 2 - Editar cadastro de Aluno");
		System.out.printf("\n 3 - Pesquisar um aluno por ID");
		System.out.printf("\n 4 - Listar Alunos");
		System.out.printf("\n 5 - Excluir um aluno, por ID");
		System.out.printf("\n 0 - Sair");

		System.out.printf("\nEscolha uma opção => ");
	}

}
