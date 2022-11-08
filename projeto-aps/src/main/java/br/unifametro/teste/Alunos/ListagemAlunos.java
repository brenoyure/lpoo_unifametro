package br.unifametro.teste.Alunos;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.Service;

public class ListagemAlunos {

	public static void main(String[] args) {

		AlunoDao dao = new AlunoDao();
		Service<Aluno> service = new AlunoService(dao);

		service.listar();

	}

}
