package br.unifametro.services;

import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifametro.modelo.Despesa;
import br.unifametro.persistencia.interfaces.DaoEditavel;
import br.unifametro.services.interfaces.EditavelService;
import br.unifametro.services.interfaces.auxiliares.PreencheDados;

@Service
public class DespesasService implements EditavelService<Despesa> {

	private final DaoEditavel<Despesa> dao;
	private final PreencheDados<Despesa> novaDespesa;

	@Autowired
	public DespesasService(DaoEditavel<Despesa> dao, PreencheDados<Despesa> novaDespesa) {
		this.dao = dao;
		this.novaDespesa = novaDespesa;
	}

	@Override
	public void cadastrar(Scanner scanner) {
		novaDespesa.getDados(scanner).ifPresent(d -> dao.salvar(d));
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
		if (nenhumCadastro()) {
			System.err.println("Nenhuma despesa cadastrada.");
			return;
		}

		dao.findAll().forEach(System.out::println);
	}

	@Override
	public void editar(Scanner scanner) {
		if (nenhumCadastro()) {
			System.err.println("Nenhuma despesa cadastrada.");
			return;
		}

		get(scanner).ifPresentOrElse(despesa -> getNovosDadosESalvar(despesa, scanner),
				() -> System.err.println("Despesa com o nome informado não encontrada."));

	}

	private void getNovosDadosESalvar(Despesa dadosAntigos, Scanner scanner) {
		System.out.printf("Editando dados de: %s", dadosAntigos);
		novaDespesa.getDados(scanner).ifPresent(novosDados -> dao.editar(dadosAntigos, novosDados));

	}

	@Override
	public void excluir(Scanner scanner) {
		if (nenhumCadastro()) {
			System.err.println("Nenhuma despesa cadastrada.");
			return;
		}

		get(scanner).ifPresentOrElse(d -> dao.excluir(d), 
				() -> System.err.println("Despesa não encontrada."));

	}

	@Override
	public boolean nenhumCadastro() {
		return getAll().count() == 0;
	}

}
