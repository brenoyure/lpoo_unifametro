package br.unifametro.teste.Despesas;

import java.util.Optional;
import java.util.Scanner;

import br.unifametro.modelo.Despesa;
import br.unifametro.persistencia.DespesasDao;
import br.unifametro.services.DespesasService;

public class BuscaDespesaPorNome {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		DespesasDao dao = new DespesasDao();
		DespesasService service = new DespesasService(dao);

		Optional<Despesa> despesa = service.getByName(scanner);

		despesa.ifPresentOrElse(System.out::println,
				() -> System.out.println("Nenhuma despesa com o nome informado encontrada."));

	}

}
