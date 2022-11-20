package br.unifametro.teste.Alunos;

import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.interfaces.Service;

public class CadastroAlunos {

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			Dao<Aluno> dao = new AlunoDao();
			Service<Aluno> servicoDeAlunos = new AlunoService(dao);
			servicoDeAlunos.cadastrar(scanner);

		}

	}

}
