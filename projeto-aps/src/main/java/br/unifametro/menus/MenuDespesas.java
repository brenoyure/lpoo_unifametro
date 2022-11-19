package br.unifametro.menus;

import static java.lang.String.format;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

import br.unifametro.modelo.Despesa;
import br.unifametro.persistencia.DespesasDao;
import br.unifametro.services.DespesasService;
import br.unifametro.services.auxiliares.DespesaPreencheDados;
import br.unifametro.services.auxiliares.DivisaoDespesas;
import br.unifametro.services.interfaces.EditavelService;

public class MenuDespesas {

	private final EditavelService<Despesa> servico;
	private final DivisaoDespesas divisaoService;
	private boolean ficarNesteMenu = true;

	public MenuDespesas() {
		this.servico = new DespesasService(new DespesasDao(), new DespesaPreencheDados());
		this.divisaoService = new DivisaoDespesas();
	}

	public void exibirMenu(Scanner sc) {

		do {
			exibir(sc);
		} while (ficarNesteMenu == true);
	}

	private void exibir(Scanner sc) {
		exibirOpcoes();
		int opcao = 0;
		ficarNesteMenu = true;

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
			System.out.println(getResumo());
			break;

		case 3:
			servico.editar(sc);
			break;

		case 4:
			servico.get(sc).ifPresentOrElse(d -> System.out.printf("\n%s\n", d),
					() -> System.err.println("Despesa com o nome informado não encontrada."));
			break;

		case 5:
			servico.listar();
			break;

		case 6:
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
		System.out.printf("\n 2 - Obter um Resumo do Mês");
		System.out.printf("\n 3 - Editar cadastro de Despesa");
		System.out.printf("\n 4 - Pesquisar uma Despesa por Nome");
		System.out.printf("\n 5 - Listar Despesas");
		System.out.printf("\n 6 - Excluir uma Despesa, por Nome");
		System.out.printf("\n 0 - Sair");

		System.out.printf("\nEscolha uma opção => ");
	}

	private String getResumo() {

		if (servico.fileNotExists()) {
			System.err.println("\nNão há despesas cadastradas para o mês atual.\n");
			return null;
		}

		StringBuilder sb = new StringBuilder();
		Long qtdDespesas = divisaoService.getQuantidadeDespesas();
		BigDecimal vTotal = divisaoService.getValorTotalDespesas();
		BigDecimal divisao = divisaoService.getDivisaoIgualitaria();

		sb.append("\n########### Resumo do Mês Atual ############\n");
		sb.append(format("\nTotal de Despesas: %d.\n", qtdDespesas));
		sb.append(format("\nValor total: R$%s.\n", vTotal));
		sb.append(format("\nCada aluno deve contribuir com: R$%s\n", divisao));
		sb.append("\n############################################\n");
		return sb.toString();

	}

}
