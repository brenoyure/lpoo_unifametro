package br.unifametro.services;

import static java.util.stream.Stream.empty;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.persistencia.interfaces.DaoEditavel;
import br.unifametro.services.auxiliares.AlunoPreencheDados;
import br.unifametro.services.auxiliares.AlunoValidaDados;
import br.unifametro.services.interfaces.EditavelService;
import br.unifametro.services.interfaces.auxiliares.PreencheDadosComEdicao;
import br.unifametro.services.interfaces.auxiliares.ValidacaoDadosEditaveis;

public class AlunoService implements EditavelService<Aluno> {

	private final DaoEditavel<Aluno> alunoDao;
	private final PreencheDadosComEdicao<Aluno> dadosAluno;
	private final ValidacaoDadosEditaveis<Aluno> validacoesService;

	public AlunoService() {
		this.alunoDao = new AlunoDao();
		this.dadosAluno = new AlunoPreencheDados();
		this.validacoesService = new AlunoValidaDados();
	}

	public AlunoService(Dao<Aluno> alunoDao) {
		this.alunoDao = (DaoEditavel<Aluno>) alunoDao;
		this.dadosAluno = new AlunoPreencheDados();
		this.validacoesService = new AlunoValidaDados();
	}

	public AlunoService(Dao<Aluno> alunoDao, PreencheDadosComEdicao<Aluno> novoAluno) {
		this.alunoDao = (DaoEditavel<Aluno>) alunoDao;
		this.dadosAluno = novoAluno;
		this.validacoesService = new AlunoValidaDados();
	}

	@Override
	public void cadastrar(Scanner scanner) {
		Aluno dadosNovoAluno = validacoesService.validar(dadosAluno.getDados(scanner));
		if (dadosNovoAluno == null)
			return;
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
		Aluno novosDados = validacoesService.validarEdicao(dadosAluno.getDadosEdicao(scanner));

		if (novosDados == null)
			return;

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

		try {
			System.out.printf("Digite o ID do Aluno: ");
			Integer id = scanner.nextInt();
			return alunoDao.findAll().filter(a -> a.getId().equals(id)).findFirst();

		} catch (InputMismatchException e) {
			System.err.printf("\nVocê digitou '%s', para o ID apenas números são permitidos.\n", scanner.next());
			System.err.printf("\nTente novamente.\n");
			return Optional.empty();
		}

	}

	public Optional<Aluno> getById(Integer id) {
		if (fileNotExists()) {
			System.err.println("Nenhum Aluno Cadastrado.");
			return Optional.empty();
		}
		return alunoDao.findAll().filter(a -> a.getId().equals(id)).findFirst();
	}

	/**
	 * <p>
	 * Busca Aluno(s) por nome ou sobrenome.
	 * </p>
	 * 
	 * Este método verifica qual ou quais aluno(s) possui(em) em seu nome ou
	 * sobrenome a
	 * sequência de caracteres <strong>(Case Sensitive)</strong>
	 * fornecida pelo usuário, e em seguida retona este ou estes aluno(s).
	 * 
	 * <p>
	 * <strong>AVISO Para utilizadores do Windows: </strong>Ao executar este método
	 * pelo VSCode ou caso esteja rodando o projeto diretamente pelo Windows
	 * PowerShell ou Prompt de Comando (CMD),
	 * pode acontecer de um aluno que possui ascento em alguma letra do nome ou no
	 * sobrenome não ser encontrado. Acredito ser um problema da codificação de
	 * caracteres do Windows, mesmo forçando para UTF-8 nos Scanners, o problema
	 * persiste.
	 * </p>
	 * 
	 * <p>
	 * Para contornar o problema você pode digitar o nome ou sobrenome do aluno que
	 * está procurando, porém, omitindo a letra que possui o ascento.
	 * Por exemplo, ao invés de 'José' você digitaria 'Jos' <= sem as aspas, claro.
	 * </p>
	 * 
	 * @param scanner entrada do teclado
	 * @return {@code Stream<Aluno>}
	 */
	public Stream<Aluno> getByName(Scanner scanner) {

		if (fileNotExists()) {
			System.err.println("Nenhum Aluno Cadastrado.");
			return empty();
		}

		if (scanner.nextLine() != "")
			scanner.nextLine();

		System.out.printf("Digite o nome ou sobrenome do Aluno: ");
		String nome = scanner.nextLine();

		Stream<Aluno> alunos = alunoDao.findAll().filter(a -> a.getNome().contains(nome));
		long quantidade = alunoDao.findAll().filter(a -> a.getNome().contains(nome)).count();

		if (quantidade == 0) {
			System.err.println("\nNenhum aluno com o nome informado encontrado.");
			return empty();
		}

		return alunos;

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
