package br.unifametro.teste.Despesas;

import java.util.Optional;
import java.util.Scanner;

import br.unifametro.modelo.Despesa;
import br.unifametro.persistencia.DespesasDao;
import br.unifametro.services.DespesasService;
import br.unifametro.services.Service;

public class BuscaDespesaPorNome {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		DespesasDao dao = new DespesasDao();
		Service<Despesa> service = new DespesasService(dao);

		Optional<Despesa> despesa = service.get(scanner);

		despesa.ifPresentOrElse(System.out::println,
				() -> System.out.println("Nenhuma despesa com o nome informado encontrada."));

	}

}
