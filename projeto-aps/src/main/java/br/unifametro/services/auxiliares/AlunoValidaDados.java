package br.unifametro.services.auxiliares;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.interfaces.auxiliares.ValidacaoDadosEditaveis;

public class AlunoValidaDados implements ValidacaoDadosEditaveis<Aluno> {

    private Dao<Aluno> alunoDao;

    public AlunoValidaDados() {
        if (alunoDao == null)
            alunoDao = new AlunoDao();
    }

    @Override
    public Aluno validar(Aluno aluno) {
        Integer id = aluno.getId();
        String nome = aluno.getNome();
        String email = aluno.getEmail();

        if (alunoJaExiste(id)) {
            System.err.println("\nJá existe um aluno com o ID informado. Cadastro não realizado.");
            return null;
        }

        if (dadosInvalidos(nome, email)) {
            System.err.println("\nNome ou Email estão em um formato inválido.");
            System.out.println("Verifique se o nome começa com uma letra maiúscula e se o e-mail contém o @.");
            return null;
        }

        return aluno;

    }

    @Override
    public Aluno validarEdicao(Aluno aluno) {
        String nome = aluno.getNome();
        String email = aluno.getEmail();

        if (dadosInvalidos(nome, email)) {
            System.err.println("\nNome ou Email estão em um formato inválido.");
            System.out.println("Verifique se o nome começa com uma letra maiúscula e se o e-mail contém o @.");
            return null;
        }

        return aluno;
    }

    private boolean alunoJaExiste(Integer id) {
        return alunoDao.findAll().anyMatch(a -> a.getId().equals(id));
    }

    private boolean dadosInvalidos(String nome, String email) {
        return !(nomeValido(nome) && !emailValido(email));
    }

    private boolean nomeValido(String nome) {
        return !(nome.contains(";")) && nome.charAt(0) == nome.toUpperCase().charAt(0);
    }

    private boolean emailValido(String email) {
        return !(email.contains(";")) && email.contains("@");
    }

}
