package br.unifametro.services;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;

public class AlunoService {

	private final AlunoDao alunoDao;

	public AlunoService(AlunoDao alunoDao) {
		this.alunoDao = alunoDao;
	}

	public void cadastrar(Scanner scanner) {
		Aluno novoAluno = getDados(scanner);
		alunoDao.salvar(novoAluno);
	}

	public void editar(Scanner scanner) throws IOException {
		listar();
		Aluno dadosAntigos = getAluno(scanner);
		Aluno dadosNovos = getDadosEdicao(scanner);
		dadosNovos.setId(dadosAntigos.getId());
		alunoDao.editar(dadosAntigos, dadosNovos);

	}

	public Aluno getAluno(Scanner scanner) {
		System.out.printf("Digite o ID do Aluno: ");
		Integer id = scanner.nextInt();
		return alunoDao.findById(id);
	}

	public Aluno getAlunoPeloNome(Scanner scanner) {
		System.out.printf("Digite o nome do Aluno: ");
		String nome = scanner.next();
		return alunoDao.findByName(nome);
	}

	public void listar() {
		alunoDao.findAll().forEach(a -> System.out.printf("id: %d, %s, %s, R$%s%n", a.getId(), a.getNome(), a.getEmail(), a.getTotalDeRendimentos()));
	}

	private Aluno getDados(Scanner scanner) {
		System.out.print("\nDigite o ID: ");
		String idStr = scanner.nextLine();
		System.out.print("Digite o nome do Aluno: ");
		String nome = scanner.nextLine();
		System.out.print("\nAgora o email: ");
		String email = scanner.nextLine();
		System.out.print("\nPor fim, o total de rendimentos: ");
		String rendimentos = scanner.nextLine();

		Integer id = Integer.valueOf(idStr);
		return new Aluno(id, nome, email, new BigDecimal(rendimentos));
	}

	private Aluno getDadosEdicao(Scanner scanner) {
		System.out.print("\nDigite o nome do Aluno: ");
		String nome = scanner.next();
		System.out.print("\nAgora o email: ");
		String email = scanner.next();
		System.out.print("\nPor fim, o total de rendimentos: ");
		BigDecimal rendimentos = scanner.nextBigDecimal();

		return new Aluno(nome, email, rendimentos);
	}

}
