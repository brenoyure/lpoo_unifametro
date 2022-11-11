package br.unifametro.services;

import java.util.Optional;
import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.Dao;

public class ReservasService implements Service<Reserva> {

	private final Dao<Reserva> dao;
	private final Service<Aluno> alunoService;

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

		Optional<Aluno> aluno = alunoService.get(scanner);
		aluno.ifPresentOrElse(r -> dao.salvar(new Reserva(aluno.get())),
				() -> System.err.println("Nenhum aluno com o ID informado encontrado."));

	}

	@Override
	public void editar(Scanner scanner) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Scanner scanner) {
		if (fileNotExists()) {
			System.err.println("Nenhuma Reserva Cadastrada.");
			return;
		}
		System.out.println("Digite o ID de um aluno para excluir as suas reservas do mês atual.");
		get(scanner).ifPresentOrElse(r -> dao.excluir(r),
				() -> System.err.println("Reservas com o ID do Aluno informado não encontradas."));

	}

	@Override
	public Optional<Reserva> get(Scanner scanner) {
		Integer alunoId = scanner.nextInt();
		return dao.findAll().filter(r -> r.getAlunoId().equals(alunoId)).findFirst();
	}

	@Override
	public void listar() {
		if (fileNotExists()) {
			System.err.println("Nenhuma Reserva cadastrada.");
			return;
		}

		dao.findAll().forEach(System.out::println);

	}

	@Override
	public boolean fileNotExists() {
		return !dao.fileExists();
	}

	private boolean nenhumAlunoCadastrado() {
		return alunoService.fileNotExists();
	}

}
