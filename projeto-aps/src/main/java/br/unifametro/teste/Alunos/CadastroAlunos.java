package br.unifametro.teste.Alunos;

import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.auxiliares.AlunoPreencheDados;
import br.unifametro.services.auxiliares.validacoes.AlunoValidaDados;
import br.unifametro.services.interfaces.Service;
import br.unifametro.services.interfaces.auxiliares.PreencheDadosComEdicao;
import br.unifametro.services.interfaces.auxiliares.validacoes.ValidacaoDadosEditaveis;

public class CadastroAlunos {

	public static void main(String[] args) {

		try (Scanner scanner = new Scanner(System.in)) {
			Dao<Aluno> dao = new AlunoDao();
			ValidacaoDadosEditaveis<Aluno> validacao = new AlunoValidaDados(dao);
			PreencheDadosComEdicao<Aluno> getDados = new AlunoPreencheDados(validacao);
			
			Service<Aluno> servicoDeAlunos = new AlunoService(dao, getDados);
			servicoDeAlunos.cadastrar(scanner);

		}

	}

}
