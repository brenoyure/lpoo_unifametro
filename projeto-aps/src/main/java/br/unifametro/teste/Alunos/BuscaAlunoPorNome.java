package br.unifametro.teste.Alunos;

import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;

public class BuscaAlunoPorNome {

	public static void main(String[] args) {

		Dao<Aluno> dao = new AlunoDao();
		AlunoService service = new AlunoService(dao, null);
		Scanner sc = new Scanner(System.in);

		service.getByName(sc);
		
	}

}
