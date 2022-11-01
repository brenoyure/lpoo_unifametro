package br.unifametro.teste.Alunos;

import java.io.IOException;
import java.util.Scanner;

import br.unifametro.persistencia.AlunoDao;
import br.unifametro.services.AlunoService;

public class EditarAluno {

	public static void main(String[] args) throws IOException {

		AlunoDao dao = new AlunoDao();
		AlunoService service = new AlunoService(dao);
		Scanner sc = new Scanner(System.in);

		service.editar(sc);

	}

}
