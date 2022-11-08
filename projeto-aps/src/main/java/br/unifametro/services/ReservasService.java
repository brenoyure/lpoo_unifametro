package br.unifametro.services;

import java.util.Optional;
import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.ReservaDao;

public class ReservasService implements Service<Reserva> {

	private final ReservaDao dao;
	private final AlunoService alunoService;

	public ReservasService(ReservaDao reservaDao, AlunoService alunoService) {
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
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<Reserva> get(Scanner scanner) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public void listar() {
		// TODO Auto-generated method stub

	}

	@Override
	public Reserva getDados(Scanner scanner) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean fileNotExists() {
		return !dao.fileExists();
	}

	private boolean nenhumAlunoCadastrado() {
		return alunoService.fileNotExists();
	}

}
