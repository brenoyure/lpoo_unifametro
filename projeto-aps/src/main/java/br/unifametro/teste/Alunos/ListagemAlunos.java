package br.unifametro.teste.Alunos;

import br.unifametro.persistencia.AlunoDao;
import br.unifametro.services.AlunoService;

public class ListagemAlunos {

	public static void main(String[] args) {

		AlunoDao dao = new AlunoDao();
		AlunoService service = new AlunoService(dao);

		service.listar();

	}

}
