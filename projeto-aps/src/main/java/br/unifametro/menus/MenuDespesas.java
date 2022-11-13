package br.unifametro.menus;

import java.util.InputMismatchException;
import java.util.Scanner;

import br.unifametro.modelo.Despesa;
import br.unifametro.persistencia.DespesasDao;
import br.unifametro.services.DespesasService;
import br.unifametro.services.Service;

public class MenuDespesas {

    private final Service<Despesa> servico;

    private boolean ficarNesteMenu = true;

    public MenuDespesas() {
        this.servico = new DespesasService(new DespesasDao());
    }

    public void exibirMenu(Scanner sc) {

        do {
            exibir(sc);
        } while (ficarNesteMenu == true);
    }

    private void exibir(Scanner sc) {
        exibirOpcoes();
        int opcao = 0;

        try {
            opcao = sc.nextInt();
        } catch (InputMismatchException e) {
            System.err.printf("Você digitou '%s', apenas números são permitidos.", sc.next());
            exibirMenu(sc);
        }

        switch (opcao) {
            case 1:
                servico.cadastrar(sc);
                break;

            case 2:
                servico.editar(sc);
                break;

            case 3:
                servico.get(sc).ifPresentOrElse(d -> System.out.printf("\n%s\n", d),
                        () -> System.err.println("Despesa com o nome informado não encontrada."));
                break;

            case 4:
                servico.listar();
                break;

            case 5:
                servico.excluir(sc);
                break;

            case 0:
                ficarNesteMenu = false;
                break;

            default:
                System.err.println("Opção não identificada. Tente novamente.");
                break;
        }

    }

    private void exibirOpcoes() {
        System.out.printf("\n####### Menu de Despesas #########\n");

        System.out.printf("\n 1 - Cadastrar Nova Despesa");
        System.out.printf("\n 2 - Editar cadastro de Despesa");
        System.out.printf("\n 3 - Pesquisar uma Despesa por Nome");
        System.out.printf("\n 4 - Listar Despesas");
        System.out.printf("\n 5 - Excluir uma Despesa, por Nome");
        System.out.printf("\n 0 - Sair");

        System.out.printf("\nEscolha uma opção => ");
    }

}
