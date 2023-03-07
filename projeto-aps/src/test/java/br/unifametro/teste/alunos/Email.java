package br.unifametro.teste.alunos;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.auxiliares.validacoes.AlunoValidaDados;

public class Email {

    private static Dao<Aluno> dao;
    private static AlunoValidaDados validacao;

    @BeforeAll
    static void setup() {
        dao = new AlunoDao();
        validacao = new AlunoValidaDados(dao);
    }

//  ---------- Teste de True e False ---------------

    @Test
    void emailValido() {
        assertTrue(validacao.emailValido("breno.brito@aluno.unifametro.edu.br"));
    }

    @Test
    void semArrolba() {
        assertFalse(validacao.emailValido("breno.britounifametro.edu.br"));
    }

    @Test
    void muitoCurtoEComArrolba() {
        assertFalse(validacao.emailValido("bre@e"));
    }

    @Test
    void muitoCurtoESemArrolba() {
        assertFalse(validacao.emailValido("bren."));
    }

//  ------- Fim dos Testes de True e False -----------

}
