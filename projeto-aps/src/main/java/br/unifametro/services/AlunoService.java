package br.unifametro.services;

import java.math.BigDecimal;
import java.util.Optional;
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

	public void editar(Scanner scanner) {
		Optional<Aluno> aluno = getAluno(scanner);
		if (aluno.isEmpty()) {
			System.err.println("Aluno não encontrado");
			return;
		}

		Aluno novosDados = getDadosEdicao(scanner);
		novosDados.setId(aluno.get().getId());

		alunoDao.editar(aluno.get(), novosDados);

	}

	public void excluir(Scanner scanner) {
		getAluno(scanner).ifPresentOrElse(a -> alunoDao.excluir(a), () -> System.err.println("Aluno não encontrado."));
	}

	public Optional<Aluno> getAluno(Scanner scanner) {
		System.out.printf("Digite o ID do Aluno: ");
		Integer id = scanner.nextInt();
		return alunoDao.findById(id);
	}

	public Optional<Aluno> getAlunoPeloNome(Scanner scanner) {
		System.out.printf("Digite o nome do Aluno: ");
		String nome = scanner.nextLine();
		return alunoDao.findByName(nome);
	}

	public void listar() {
		if (alunoDao.findAll().count() == 0) {
			System.err.println("Nenhum Aluno Cadastrado.");
			return;
		}

		alunoDao.findAll().forEach(a -> System.out.printf("id: %d, %s, %s, R$%s%n", a.getId(), a.getNome(),
				a.getEmail(), a.getTotalDeRendimentos()));
	}

	private Aluno getDados(Scanner scanner) {
		System.out.print("\nDigite o ID: ");
		Integer id = scanner.nextInt();
		scanner.nextLine();
		System.out.print("Digite o nome do Aluno: ");
		String nome = scanner.nextLine();
		System.out.print("\nAgora o email: ");
		String email = scanner.nextLine();
		System.out.print("\nPor fim, o total de rendimentos: ");
		BigDecimal rendimentos = scanner.nextBigDecimal();

		return new Aluno(id, nome, email, rendimentos);
	}

	private Aluno getDadosEdicao(Scanner scanner) {
		if (scanner.nextLine() != "")
			scanner.nextLine();
		System.out.print("\nDigite o nome do Aluno: ");
		String nome = scanner.nextLine();
		System.out.print("\nAgora o email: ");
		String email = scanner.nextLine();
		System.out.print("\nPor fim, o total de rendimentos: ");
		BigDecimal rendimentos = scanner.nextBigDecimal();

		return new Aluno(nome, email, rendimentos);
	}

}
