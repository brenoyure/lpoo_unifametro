package br.unifametro.teste.Despesas;

import br.unifametro.persistencia.DespesasDao;
import br.unifametro.services.DespesasService;

public class ListagemDespesas {

	public static void main(String[] args) {

		DespesasDao dao = new DespesasDao();
		DespesasService serv = new DespesasService(dao);

		serv.listar();

	}

}
