package br.unifametro.services;

import static java.util.Optional.empty;

import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.interfaces.Service;

@org.springframework.stereotype.Service
public class ReservasService implements Service<Reserva> {

	private final Dao<Reserva> dao;
	private final Service<Aluno> alunoService;

	@Autowired
	public ReservasService(Dao<Reserva> reservaDao, Service<Aluno> alunoService) {
		this.dao = reservaDao;
		this.alunoService = alunoService;
	}

	@Override
	public void cadastrar(Scanner scanner) {
		if (nenhumAlunoCadastrado()) {
			System.err.println(
					"Para realizar uma reserva, é necessário possuir pelo menos 1 aluno cadastrado no sistema.");
			return;
		}

		alunoService.get(scanner).ifPresentOrElse(aluno -> dao.salvar(new Reserva(aluno)), 
				() -> System.err.println("Aluno com o ID informado encontrado."));
		
	}

	@Override
	public void excluir(Scanner scanner) {
		if (nenhumCadastro()) {
			System.err.println("Nenhuma Reserva Cadastrada.");
			return;
		}
		System.out.printf("Para excluir, ");
		get(scanner).ifPresentOrElse(r -> dao.excluir(r),
				() -> System.err.println("Reserva com o ID do Aluno informado não encontrada."));

	}

	@Override
	public Optional<Reserva> get(Scanner scanner) {
		if (nenhumCadastro() || nenhumAlunoCadastrado()) {
			System.err.println("Nenhuma Reserva Cadastrada.");
			return empty();
		}

		try {
			System.out.printf("\nDigite o ID do Aluno que possui a reserva: ");
			Integer alunoId = scanner.nextInt();
			return dao.findAll().filter(r -> r.getAlunoId().equals(alunoId)).findFirst();

		} catch (InputMismatchException e) {
			System.err.printf("\nVocê digitou '%s', para o ID apenas números são permitidos.", scanner.next());
			System.err.printf("\nTente novamente.\n");
			return empty();
		}

	}

	@Override
	public Stream<Reserva> getAll() {
		return dao.findAll();
	}

	@Override
	public void listar() {
		if (nenhumCadastro()) {
			System.err.println("Nenhuma Reserva cadastrada.");
			return;
		}

		dao.findAll().forEach(System.out::println);

	}

	@Override
	public boolean nenhumCadastro() {
		return dao.findAll().count() == 0;
	}

	private boolean nenhumAlunoCadastrado() {
		return alunoService.nenhumCadastro();
	}

}
