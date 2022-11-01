package br.unifametro.teste.Alunos;

import java.io.IOException;
import java.util.Scanner;

import br.unifametro.persistencia.AlunoDao;
import br.unifametro.services.AlunoService;

public class CadastroAlunos {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);

		AlunoDao alunoDao = new AlunoDao();
		AlunoService servicoDeAlunos = new AlunoService(alunoDao);
		servicoDeAlunos.cadastrar(scanner);

	}

}
