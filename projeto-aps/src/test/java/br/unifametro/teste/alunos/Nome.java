package br.unifametro.teste.alunos;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.auxiliares.validacoes.AlunoValidaDados;

public class Nome {

    private static Dao<Aluno> dao;
    private static AlunoValidaDados validacao;

    @BeforeAll
    static void setup() {
        dao = new AlunoDao();
        validacao = new AlunoValidaDados(dao);
    }


    // ---------- Teste de True e False ---------------

    @Test
    void apenasNomeValido() {
        assertTrue(validacao.nomeValido("Breno"));
    }

    @Test
    void nomeComSobreNomeValido() {
        assertTrue(validacao.nomeValido("Breno Yuri"));
    }

    @Test
    void nomeESobreNomeComPontoEVirgula() {
        assertFalse(validacao.nomeValido("Bren; Yuri"));
    }

    @Test
    void nomeComPontoEVirgula() {
        assertFalse(validacao.nomeValido("Bren;"));
    }

    @Test
    void nomeCurto() {
        assertFalse(validacao.nomeValido("Bre"));
    }

    @Test
    void comecaComLetraMinuscula() {
        assertFalse(validacao.nomeValido("breno"));
    }

    // ------- Fim dos Testes de True e False -----------

}
