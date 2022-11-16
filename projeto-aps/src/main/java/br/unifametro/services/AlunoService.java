package br.unifametro.services;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.persistencia.interfaces.DaoEditavel;
import br.unifametro.services.auxiliares.AlunoPreencheDados;
import br.unifametro.services.interfaces.EditavelService;
import br.unifametro.services.interfaces.auxiliares.PreencheDadosComEdicao;

public class AlunoService implements EditavelService<Aluno> {

	private final DaoEditavel<Aluno> alunoDao;
	private final PreencheDadosComEdicao<Aluno> dadosAluno;

	public AlunoService() {
		this.alunoDao = new AlunoDao();
		this.dadosAluno = new AlunoPreencheDados();
	}

	public AlunoService(Dao<Aluno> alunoDao) {
		this.alunoDao = (DaoEditavel<Aluno>) alunoDao;
		this.dadosAluno = new AlunoPreencheDados();
	}

	public AlunoService(Dao<Aluno> alunoDao, PreencheDadosComEdicao<Aluno> novoAluno) {
		this.alunoDao = (DaoEditavel<Aluno>) alunoDao;
		this.dadosAluno = novoAluno;
	}

	@Override
	public void cadastrar(Scanner scanner) {
		Aluno dadosNovoAluno = dadosAluno.getDados(scanner);
		alunoDao.salvar(dadosNovoAluno);
	}

	@Override
	public void editar(Scanner scanner) {
		if (fileNotExists()) {
			System.err.println("Nenhum Aluno Cadastrado.");
			return;
		}
		Optional<Aluno> aluno = get(scanner);
		if (aluno.isEmpty()) {
			System.err.println("Aluno com o ID informado não encontrado.");
			return;
		}

		System.out.printf("Editando dados de: %s", aluno.get());
		Aluno novosDados = dadosAluno.getDadosEdicao(scanner);
		novosDados.setId(aluno.get().getId());

		alunoDao.editar(aluno.get(), novosDados);

	}

	@Override
	public void excluir(Scanner scanner) {
		if (fileNotExists()) {
			System.err.println("Nenhum Aluno Cadastrado.");
			return;
		}
		System.out.printf("Para excluir, ");
		get(scanner).ifPresentOrElse(a -> alunoDao.excluir(a),
				() -> System.err.println("Aluno com o ID informado não encontrado."));
	}

	@Override
	/**
	 * Método que recebe um ID, via scanner, e devolve um Optional de Aluno
	 * 
	 * @param Scanner scanner
	 * @return Optional de Aluno
	 */
	public Optional<Aluno> get(Scanner scanner) {
		if (fileNotExists()) {
			System.err.println("Nenhum Aluno Cadastrado.");
			return Optional.empty();
		}

		System.out.printf("Digite o ID do Aluno: ");
		Integer id = scanner.nextInt();
		return alunoDao.findAll().filter(a -> a.getId().equals(id)).findFirst();
	}

	public Optional<Aluno> getById(Integer id) {
		if (fileNotExists()) {
			System.err.println("Nenhum Aluno Cadastrado.");
			return Optional.empty();
		}
		return alunoDao.findAll().filter(a -> a.getId().equals(id)).findFirst();
	}

	public Optional<Aluno> getByName(Scanner scanner) {
		if (fileNotExists()) {
			System.err.println("Nenhum Aluno Cadastrado.");
			return Optional.empty();
		}
		if (scanner.nextLine() != "")
			scanner.nextLine();
		System.out.printf("Digite o nome do Aluno: ");
		String nome = scanner.nextLine();
		return alunoDao.findAll().filter(a -> a.getNome().startsWith(nome)).findFirst();
	}

	@Override
	public Stream<Aluno> getAll() {
		return alunoDao.findAll();
	}

	@Override
	public void listar() {
		if (fileNotExists()) {
			System.err.println("Nenhum Aluno Cadastrado.");
			return;
		}

		alunoDao.findAll().forEach(System.out::println);
	}

	@Override
	public boolean fileNotExists() {
		return !alunoDao.fileExists();

	}

}
