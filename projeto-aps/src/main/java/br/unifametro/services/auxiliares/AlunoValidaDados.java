package br.unifametro.services.auxiliares;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.interfaces.auxiliares.ValidacaoDadosEditaveis;

@Service
public class AlunoValidaDados implements ValidacaoDadosEditaveis<Aluno> {

    private Dao<Aluno> alunoDao;

    @Autowired
    public AlunoValidaDados(Dao<Aluno> alunoDao) {
		this.alunoDao = alunoDao;
	}

	@Override
    public Optional<Aluno> validar(Aluno aluno) {
        Integer id = aluno.getId();
        String nome = aluno.getNome();
        String email = aluno.getEmail();

        if (alunoJaExiste(id)) {
            System.err.printf("\nJá existe um aluno com o ID informado. Cadastro não realizado.\n");
            return empty();
        }

        if (dadosInvalidos(nome, email)) {
            System.err.println("\nNome ou Email estão em um formato inválido.");
            System.out.println("Verifique se o nome começa com uma letra maiúscula e se o e-mail contém o @.");
            return empty();
        }

        return ofNullable(aluno);

    }

    @Override
    public Optional<Aluno> validarEdicao(Aluno aluno) {
        String nome = aluno.getNome();
        String email = aluno.getEmail();

        if (dadosInvalidos(nome, email)) {
            System.err.println("\nNome ou Email estão em um formato inválido.");
            System.out.println("Verifique se o Nome começa com uma letra maiúscula e/ou se o E-mail contém o @.");
            return null;
        }

        return of(aluno);
    }

    private boolean alunoJaExiste(Integer id) {
        return alunoDao.findAll().anyMatch(a -> a.getId().equals(id));
    }

    private boolean dadosInvalidos(String nome, String email) {
        return !(nomeValido(nome) && emailValido(email));
    }

    private boolean nomeValido(String nome) {
        return !(nome.contains(";")) && nome.charAt(0) == nome.toUpperCase().charAt(0);
    }

    private boolean emailValido(String email) {
        return !email.contains(";") && email.contains("@");
    }

}
