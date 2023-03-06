package br.unifametro.teste.Alunos;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.auxiliares.AlunoValidaDados;
import br.unifametro.services.auxiliares.exceptions.validacoes.aluno.AlunoValidationException;
import br.unifametro.services.interfaces.auxiliares.ValidacaoDadosEditaveis;

public class ValidacaoAlunos {

	@Test
	void deveLancarUmaExceptionCasoONomeEstejaEmUmFormatoInvalido() {
		Dao<Aluno> dao = new AlunoDao();
		ValidacaoDadosEditaveis<Aluno> validacao = new AlunoValidaDados(dao);

		assertThrows(AlunoValidationException.class, () -> validacao
				.validar(new Aluno("Breno", "breno.brito@aluno.unifametro.edu.br", new BigDecimal("2100.00"))));
	}

}
