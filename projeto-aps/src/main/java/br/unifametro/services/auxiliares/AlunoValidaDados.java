package br.unifametro.services.auxiliares;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.auxiliares.exceptions.validacoes.aluno.AlunoValidationException;
import br.unifametro.services.interfaces.auxiliares.ValidacaoDadosEditaveis;

@Service
public class AlunoValidaDados implements ValidacaoDadosEditaveis<Aluno> {

    private Dao<Aluno> alunoDao;

    @Autowired
    public AlunoValidaDados(Dao<Aluno> alunoDao) {
		this.alunoDao = alunoDao;
	}

	@Override
    public Aluno validar(Aluno aluno) throws AlunoValidationException {
        Integer id = aluno.getId();
        String nome = aluno.getNome();
        String email = aluno.getEmail();

        if (alunoJaExiste(id)) {
            throw new AlunoValidationException("\nJá existe um aluno com o ID informado. Cadastro não realizado.\n");
        }

        if (dadosInvalidos(nome, email)) {
        	StringBuilder sb = new StringBuilder("\nNome ou Email estão em um formato inválido.");
        	sb.append("\nVerifique se o nome começa com uma letra maiúscula e se o e-mail contém o @.");
            throw new AlunoValidationException(sb.toString());
        }

        return aluno;

    }
	
	
    @Override
    public Aluno validarEdicao(Aluno aluno) throws AlunoValidationException {
        String nome = aluno.getNome();
        String email = aluno.getEmail();

        if (dadosInvalidos(nome, email)) {
        	StringBuilder sb = new StringBuilder("\nNome ou Email estão em um formato inválido.");
        	sb.append("\nVerifique se o nome começa com uma letra maiúscula e se o e-mail contém o @.");
            throw new AlunoValidationException(sb.toString());
        }

        return aluno;
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
