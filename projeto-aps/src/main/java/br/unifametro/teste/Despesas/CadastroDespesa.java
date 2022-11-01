package br.unifametro.teste.Despesas;

import java.util.Scanner;

import br.unifametro.persistencia.DespesasDao;
import br.unifametro.services.DespesasService;

public class CadastroDespesa {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		DespesasService serv = new DespesasService(new DespesasDao());

		serv.cadastrar(sc);

		sc.close();

	}

}
