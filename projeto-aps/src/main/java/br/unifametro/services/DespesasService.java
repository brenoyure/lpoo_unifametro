package br.unifametro.services;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

import br.unifametro.modelo.Despesa;
import br.unifametro.modelo.Prioridade;
import br.unifametro.persistencia.DespesasDao;

public class DespesasService implements Service<Despesa> {

	private final DespesasDao despesasDao;

	public DespesasService(DespesasDao dao) {
		this.despesasDao = dao;
	}

	@Override
	public void cadastrar(Scanner scanner) {
		Despesa despesa = getDados(scanner);
		despesasDao.salvar(despesa);
	}

	@Override
	public Optional<Despesa> get(Scanner scanner) {
		System.out.printf("Digite o nome da Despesa: ");
		if (scanner.nextLine() != "")
			scanner.nextLine();
		String nome = scanner.nextLine();
		return despesasDao.findByName(nome);
	}

	@Override
	public void listar() {
		if (fileNotExists()) {
			System.err.println("Nenhuma despesa cadastrada.");
			return;
		}

		despesasDao.findAll().forEach(System.out::println);
	}

	@Override
	public void editar(Scanner scanner) {

		if (fileNotExists()) {
			System.err.println("Nenhuma despesa cadastrada.");
			return;
		}

		Optional<Despesa> despesa = get(scanner);

		if (despesa.isEmpty()) {
			System.err.println("Despesa com o Nome informado não encontrada.");
			return;
		}

		System.out.printf("Editando dados de: %s\n", despesa.get());
		despesasDao.editar(despesa.get(), getDados(scanner));

	}

	@Override
	public void excluir(Scanner scanner) {
		if (fileNotExists()) {
			System.err.println("Nenhuma despesa cadastrada.");
			return;
		}

		get(scanner).ifPresentOrElse(d -> despesasDao.excluir(d), () -> System.err.println("Despesa não encontrada."));

	}

	public Despesa getDados(Scanner scanner) {
		if (scanner.nextLine() != "")
			scanner.nextLine();

		System.out.print("Digite o nome da Despesa: ");
		String nome = scanner.nextLine();

		System.out.print("\nForneça uma descrição: ");
		String descricao = scanner.nextLine();

		System.out.print("\nInforme a categoria: ");
		String categoria = scanner.nextLine();

		System.out.print("\nPor fim, o valor da despesa R$: ");
		BigDecimal valor = scanner.nextBigDecimal();

		return new Despesa(nome, descricao, categoria, Prioridade.MEDIA, valor);
	}

	@Override
	public boolean fileNotExists() {
		return !despesasDao.fileExists();
	}

}
