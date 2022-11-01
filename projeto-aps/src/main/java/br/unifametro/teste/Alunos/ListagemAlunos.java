package br.unifametro.teste.Alunos;

import java.util.stream.Stream;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;

public class ListagemAlunos {

	public static void main(String[] args) throws Exception {

		AlunoDao dao = new AlunoDao();
		Stream<Aluno> alunos = dao.findAll();
		
		alunos.forEach(System.out::println);

	}

}
