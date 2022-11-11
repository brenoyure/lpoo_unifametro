package br.unifametro.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.Service;

public class MenuAlunos {

	private final Service<Aluno> servico;
	private Integer opcao = 0;

	public MenuAlunos() {
		this.servico = new AlunoService(new AlunoDao());
	}

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
				servico.get(sc).ifPresentOrElse(System.out::println, () -> System.err.println("Aluno não encontrado"));
				servico.listar();
				exibirMenu(sc);
				break;

			case 4:
				servico.listar();
				exibirMenu(sc);
				break;

			case 5:
				servico.excluir(sc);
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
		System.out.println("\n####### Menu de Alunos #########\n");

		System.out.printf("\n 1 - Cadastrar Novo Aluno");
		System.out.printf("\n 2 - Editar cadastro de Aluno");
		System.out.printf("\n 3 - Pesquisar um aluno por ID");
		System.out.printf("\n 4 - Listar Alunos");
		System.out.printf("\n 5 - Excluir um aluno, por ID");
		System.out.printf("\n 0 - Sair");

		System.out.printf("\nEscolha uma opção => ");
	}

}
