package br.unifametro.teste.Alunos;

import java.io.IOException;
import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.auxiliares.AlunoPreencheDados;
import br.unifametro.services.auxiliares.AlunoValidaDados;
import br.unifametro.services.interfaces.EditavelService;
import br.unifametro.services.interfaces.auxiliares.PreencheDadosComEdicao;
import br.unifametro.services.interfaces.auxiliares.ValidacaoDadosEditaveis;

public class ExcluirAluno {

	public static void main(String[] args) throws IOException {

		try (Scanner scanner = new Scanner(System.in)) {
			Dao<Aluno> dao = new AlunoDao();
			ValidacaoDadosEditaveis<Aluno> validacao = new AlunoValidaDados(dao);
			PreencheDadosComEdicao<Aluno> getDados = new AlunoPreencheDados(validacao);
			EditavelService<Aluno> servicoDeAlunos = new AlunoService(dao, getDados);
			servicoDeAlunos.excluir(scanner);

		}

	}

}
