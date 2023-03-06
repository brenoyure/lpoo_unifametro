package br.unifametro.services.auxiliares;

import static br.unifametro.modelo.Prioridade.values;
import static java.lang.Math.abs;
import static java.util.Optional.ofNullable;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.unifametro.modelo.Despesa;
import br.unifametro.modelo.Prioridade;
import br.unifametro.services.interfaces.auxiliares.PreencheDados;

@Service
public class DespesaPreencheDados implements PreencheDados<Despesa> {

	@Override
	public Optional<Despesa> getDados(Scanner scanner) {
		if (scanner.nextLine() != "")
			scanner.nextLine();

		System.out.printf("\nDigite o nome da Despesa: ");
		String nome = scanner.nextLine();

		System.out.printf("\nForneça uma descrição: ");
		String descricao = scanner.nextLine();

		System.out.printf("\nInforme a categoria: ");
		String categoria = scanner.nextLine();

		int j = 0;
		Prioridade prioridade = null;

		try {
			System.out.printf("\nUse o teclado numérico, para o nível de Prioridade, sendo: \n%s\n => ",
					exibePrioridades());
			j = abs(scanner.nextInt());
			prioridade = Prioridade.values()[--j];

		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.printf("\nApenas as opções de 1 a %d são permitidas.\n", values().length);
			return null;
		}

		catch (InputMismatchException e) {
			System.err.printf("\nVocê digitou '%s', apenas números são permitidos.\n", scanner.next());
			return null;
		}

		if (scanner.nextLine() != "")
			scanner.nextLine();

		BigDecimal valor = null;
		try {
			System.out.printf("\nDICA: Ao digitar, separe o decimal com vírgula, por exemplo '120,00' ");
			System.out.printf("\nPor fim, o valor da despesa R$: ");
			valor = scanner.nextBigDecimal();
		} catch (InputMismatchException e) {
			System.err.printf("\nPara valores monetários, apenas números são permitidos.\n", scanner.next());
			return null;
		}

		return ofNullable(new Despesa(nome, descricao, categoria, prioridade, valor));
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
