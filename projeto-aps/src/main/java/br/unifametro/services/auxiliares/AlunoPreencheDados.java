package br.unifametro.services.auxiliares;

import static java.lang.Math.abs;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.services.interfaces.auxiliares.PreencheDadosComEdicao;
import br.unifametro.services.interfaces.auxiliares.ValidacaoDadosEditaveis;

public class AlunoPreencheDados implements PreencheDadosComEdicao<Aluno> {

    private ValidacaoDadosEditaveis<Aluno> validacoes;

    public AlunoPreencheDados() {
        if (validacoes == null)
            validacoes = new AlunoValidaDados();
    }

    @Override
    public Aluno getDados(Scanner keyboardInput) {

        Integer id = null;
        String nome = null;
        String email = null;
        BigDecimal rendimentos = null;

        try {
            System.out.print("\nDigite o ID: ");
            id = abs(keyboardInput.nextInt());
        } catch (InputMismatchException e) {
            System.err.printf("\nVocê digitou '%s', para o ID apenas números são permitidos.\n", keyboardInput.next());
            System.err.printf("\nTente novamente.\n");
            return null;

        }

        if (keyboardInput.nextLine() != "")
            keyboardInput.nextLine();

        System.out.print("Digite o nome do Aluno: ");
        nome = keyboardInput.nextLine();

        System.out.print("\nAgora o email: ");
        email = keyboardInput.nextLine();

        try {
            System.out.printf("\nDICA: Ao digitar, separe o decimal com vírgula, por exemplo '120,00' ");
            System.out.print("\nPor fim, o total de rendimentos: ");
            rendimentos = keyboardInput.nextBigDecimal().abs();

        } catch (InputMismatchException e) {
            System.err.printf("\nPara valores monetários, apenas números são permitidos.\n", keyboardInput.next());
            System.err.printf("\nTente novamente.\n");
            return null;

        }

        return validacoes.validar(new Aluno(id, nome, email, rendimentos));
    }

    @Override
    public Aluno getDadosEdicao(Scanner keyboardInput) {
        if (keyboardInput.nextLine() != "")
            keyboardInput.nextLine();
        System.out.print("\nDigite o nome do Aluno: ");
        String nome = keyboardInput.nextLine();
        System.out.print("\nAgora o email: ");
        String email = keyboardInput.nextLine();

        System.out.printf("\nDICA: Ao digitar, separe o decimal com vírgula, por exemplo '120,00' ");
        System.out.print("\nPor fim, o total de rendimentos: R$");
        BigDecimal rendimentos = keyboardInput.nextBigDecimal();

        return validacoes.validarEdicao(new Aluno(nome, email, rendimentos));
    }

}
