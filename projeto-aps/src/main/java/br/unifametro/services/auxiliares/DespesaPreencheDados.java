package br.unifametro.services.auxiliares;

import java.math.BigDecimal;
import java.util.Scanner;

import br.unifametro.modelo.Despesa;
import br.unifametro.modelo.Prioridade;
import br.unifametro.services.interfaces.auxiliares.PreencheDados;

public class DespesaPreencheDados implements PreencheDados<Despesa> {

    @Override
    public Despesa getDados(Scanner scanner) {
        if (scanner.nextLine() != "")
            scanner.nextLine();

        System.out.printf("Digite o nome da Despesa: ");
        String nome = scanner.nextLine();

        System.out.printf("\nForneça uma descrição: ");
        String descricao = scanner.nextLine();

        System.out.printf("\nInforme a categoria: ");
        String categoria = scanner.nextLine();

        System.out.printf("\nUse o teclado numérico, para o nível de Prioridade,\nsendo: %s\n =>", exibePrioridades());
        int j = scanner.nextInt();
        Prioridade prioridade = Prioridade.values()[--j];

        if (scanner.nextLine() != "")
            scanner.nextLine();

        System.out.printf("\nDICA: Ao digitar, separe o decimal com vírgula, por exemplo '120,00' ");
        System.out.printf("\nPor fim, o valor da despesa R$: ");
        BigDecimal valor = scanner.nextBigDecimal();

        return new Despesa(nome, descricao, categoria, prioridade, valor);
    }

    private String exibePrioridades() {
        int i = 1;
        StringBuilder sb = new StringBuilder();

        for (Prioridade p : Prioridade.values()) {
            sb.append(String.format("%d para %s ", i, p));
            i++;
        }
        return sb.toString();
    }

}
