package br.unifametro.services.auxiliares;

import java.math.BigDecimal;
import java.util.Scanner;

import br.unifametro.modelo.Aluno;
import br.unifametro.services.interfaces.auxiliares.PreencheDadosComEdicao;

public class AlunoPreencheDados implements PreencheDadosComEdicao<Aluno> {

    public Aluno getDados(Scanner keyboardInput) {
        System.out.print("\nDigite o ID: ");
        Integer id = keyboardInput.nextInt();
        keyboardInput.nextLine();
        System.out.print("Digite o nome do Aluno: ");
        String nome = keyboardInput.nextLine();
        System.out.print("\nAgora o email: ");
        String email = keyboardInput.nextLine();
        System.out.print("\nPor fim, o total de rendimentos: ");
        BigDecimal rendimentos = keyboardInput.nextBigDecimal();

        return new Aluno(id, nome, email, rendimentos);
    }

    @Override
    public Aluno getDadosEdicao(Scanner scanner) {
        if (scanner.nextLine() != "")
            scanner.nextLine();
        System.out.print("\nDigite o nome do Aluno: ");
        String nome = scanner.nextLine();
        System.out.print("\nAgora o email: ");
        String email = scanner.nextLine();
        System.out.print("\nPor fim, o total de rendimentos: ");
        BigDecimal rendimentos = scanner.nextBigDecimal();

        return new Aluno(nome, email, rendimentos);
    }

}
