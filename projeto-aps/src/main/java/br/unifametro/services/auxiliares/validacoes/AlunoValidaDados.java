package br.unifametro.services.auxiliares.validacoes;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.unifametro.modelo.Aluno;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.interfaces.auxiliares.validacoes.ValidacaoDadosEditaveis;

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
            System.err.println("Aluno com o Id informado já existe.");
            return empty();
        }

        if (!dadosValidos(nome, email)) {
            System.err.println("Nome ou E-mail estão em um formato inválido.");
            System.err.println("Verifique se o nome começa com letra maiúscula, e se o e-mail contém o '@'.");
            return empty();
        }

        return ofNullable(aluno);

    }

    @Override
    public Optional<Aluno> validarEdicao(Aluno aluno) {
        String nome = aluno.getNome();
        String email = aluno.getEmail();

        if (dadosValidos(nome, email)) {
            return ofNullable(aluno);
        }

        return empty();

    }

    /**
     * Verifica que o aluno já existe, baseado no id.
     * 
     * @param id
     * @return true se o dado Id existir na {@code Dao<T>}.
     * @throws AlunoValidationException
     */
    private boolean alunoJaExiste(Integer id) {
        return alunoDao.findAll().anyMatch(a -> a.getId().equals(id));
    }

    /**
     * Verifica se os dados são válidos.
     * 
     * @param nome
     * @param E-mail
     * @return true se os dados fornecidos forem válidos e não for lançada nenhuma
     *         {@code ValidationException}
     * @throws AlunoValidationException
     */
    public boolean dadosValidos(String nome, String email) {
        return (nomeValido(nome) && emailValido(email));
    }

    /**
     * Verifica se o nome fornecido é válido, ao começar com letra maiúscula,
     * possuir mais que 5 caractéres e não possuir o ';'.
     * 
     * @param nome
     * @return true se o nome estiver em um formato válido.
     * @throws AlunoValidationException
     */
    public boolean nomeValido(String nome) {
        return (!nome.contains(";")) && nomeComecaComLetraMaiuscula(nome) && minimoPermitido(nome, 3);
    }

    /**
     * Verifica se o e-mail é valido.
     * Deve possuir mais que 5 caracteres, o '@' e não possuir o ponto e vírgula
     * ';'.
     * 
     * @param email
     * @return true se o e-mail fornecido estiver em um formato válido
     * @throws AlunoValidationException
     */
    public boolean emailValido(String email) {
        return (!email.contains(";") && email.contains("@") && (minimoPermitido(email, 5)));

    }

    /**
     * Verifica se a {@code String} atende ao mínimo de caracteres permitido.
     * 
     * @param s
     * @param minimoDeCaracteres
     * @return se a {@code String} atender ao mínimo de caracteres permitido.
     * @throws ValidationException
     */
    private boolean minimoPermitido(String s, int minimoDeCaracteres) {
        return s.length() > minimoDeCaracteres;
    }

    /**
     * Verifica se o nome começa com letra maiúscula.
     * 
     * @param nome
     * @return true se o nome começar com letra maiúscula
     * @throws AlunoValidationException
     */
    private boolean nomeComecaComLetraMaiuscula(String nome) {
        return (nome.charAt(0) == nome.toUpperCase().charAt(0));
    }

}
