package br.unifametro.services;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import br.unifametro.modelo.Despesa;
import br.unifametro.persistencia.interfaces.DaoEditavel;
import br.unifametro.services.interfaces.EditavelService;
import br.unifametro.services.interfaces.auxiliares.PreencheDados;

public class DespesasService implements EditavelService<Despesa> {

	private final DaoEditavel<Despesa> dao;
	private final PreencheDados<Despesa> novaDespesa;

	public DespesasService(DaoEditavel<Despesa> dao, PreencheDados<Despesa> novaDespesa) {
		this.dao = dao;
		this.novaDespesa = novaDespesa;
	}

	@Override
	public void cadastrar(Scanner scanner) {
		Despesa despesa = novaDespesa.getDados(scanner);
		if (despesa == null)
			return;
		dao.salvar(despesa);
	}

	@Override
	public Optional<Despesa> get(Scanner scanner) {
		System.out.printf("Digite o nome da Despesa: ");
		if (scanner.nextLine() != "")
			scanner.nextLine();
		String nome = scanner.nextLine();
		return dao.findAll().filter(d -> d.getNome().startsWith(nome)).findFirst();
	}

	@Override
	public Stream<Despesa> getAll() {
		return dao.findAll();
	}

	@Override
	public void listar() {
		if (fileNotExists()) {
			System.err.println("Nenhuma despesa cadastrada.");
			return;
		}

		dao.findAll().forEach(System.out::println);
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
		dao.editar(despesa.get(), novaDespesa.getDados(scanner));

	}

	@Override
	public void excluir(Scanner scanner) {
		if (fileNotExists()) {
			System.err.println("Nenhuma despesa cadastrada.");
			return;
		}

		get(scanner).ifPresentOrElse(d -> dao.excluir(d), () -> System.err.println("Despesa não encontrada."));

	}

	@Override
	public boolean fileNotExists() {
		return getAll().count() == 0;
	}

}
