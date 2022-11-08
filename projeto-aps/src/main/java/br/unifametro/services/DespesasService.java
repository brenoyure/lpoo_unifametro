package br.unifametro.services;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Scanner;

import br.unifametro.modelo.Despesa;
import br.unifametro.modelo.Prioridade;
import br.unifametro.persistencia.DespesasDao;

public class DespesasService {

	private final DespesasDao despesasDao;

	public DespesasService(DespesasDao dao) {
		this.despesasDao = dao;
	}

	public void cadastrar(Scanner scanner) {
		Despesa despesa = getDados(scanner);
		despesasDao.salvar(despesa);
	}

	public Optional<Despesa> getByName(Scanner scanner) {
		System.out.printf("Digite o nome da Despesa: ");
		String nome = scanner.nextLine();
		return despesasDao.findByName(nome);
	}

	public void listar() {
		if (fileNotExists()) {
			System.err.println("Nenhuma despesa cadastrada.");
			return;
		}

		despesasDao.findAll().forEach(d -> System.out.printf("%s, %s, %s, %s, %s%n", d.getNome(), d.getCategoria(),
				d.getDescricao(), d.getPRIORIDADE(), d.getValor()));
	}

	public void editar(Scanner scanner) {

		Optional<Despesa> despesa = getByName(scanner);

		if (despesa.isEmpty()) {
			System.err.println("Nenhuma despesa com o nome informado encontrada.");
			return;
		}

		despesasDao.editar(despesa.get(), getDados(scanner));

	}

	public void excluir(Scanner scanner) {
		if (fileNotExists()) {
			System.err.println("Nenhuma despesa cadastrada.");
			return;
		}

		getByName(scanner).ifPresentOrElse(d -> despesasDao.excluir(d),
				() -> System.err.println("Despesa não encontrada."));

	}

	private Despesa getDados(Scanner scanner) {
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

	private boolean fileNotExists() {
		return !despesasDao.fileExists();
	}

}
