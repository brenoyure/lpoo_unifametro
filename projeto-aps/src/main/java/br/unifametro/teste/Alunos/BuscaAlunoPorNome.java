package br.unifametro.teste.Alunos;

import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.services.AlunoService;

public class BuscaAlunoPorNome{

	public static void main(String[] args) {
		
		AlunoDao dao = new AlunoDao();
		AlunoService service = new AlunoService(dao);
		Scanner sc = new Scanner(System.in);

		Aluno aluno = service.getAlunoPeloNome(sc);
		System.out.println(aluno);

	}

}
