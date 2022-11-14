package br.unifametro.teste.Despesas;

import java.util.Scanner;

import br.unifametro.persistencia.DespesasDao;
import br.unifametro.services.DespesasService;
import br.unifametro.services.auxiliares.DespesaPreencheDados;

public class EditarDespesa {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		DespesasDao dao = new DespesasDao();
		DespesasService services = new DespesasService(dao, new DespesaPreencheDados());

		services.editar(sc);

	}

}
