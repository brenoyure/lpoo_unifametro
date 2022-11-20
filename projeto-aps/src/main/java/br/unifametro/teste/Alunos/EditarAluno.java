package br.unifametro.teste.Alunos;

import java.io.IOException;
import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.interfaces.EditavelService;

public class EditarAluno {

	public static void main(String[] args) throws IOException {

		try (Scanner scanner = new Scanner(System.in)) {
			Dao<Aluno> dao = new AlunoDao();
			EditavelService<Aluno> servicoDeAlunos = new AlunoService(dao);
			servicoDeAlunos.editar(scanner);

		}

	}

}
