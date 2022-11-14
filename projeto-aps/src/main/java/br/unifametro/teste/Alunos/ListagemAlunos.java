package br.unifametro.teste.Alunos;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;

public class ListagemAlunos {

	public static void main(String[] args) {

		Dao<Aluno> dao = new AlunoDao();
		dao.findAll().forEach(System.out::println);

	}

}
