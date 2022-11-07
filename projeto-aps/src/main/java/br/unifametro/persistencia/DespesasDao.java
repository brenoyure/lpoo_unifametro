package br.unifametro.persistencia;

import static br.unifametro.modelo.Prioridade.PODE_ESPERAR;
import static java.lang.System.lineSeparator;
import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import br.unifametro.modelo.Despesa;

/**
 * Classe responsável pelas operações de IO com o arquivo de persistência.
 * 
 * @see br.unifametro.services.DespesasService
 */
public class DespesasDao {

	private final File file = new File(getFileName());

	public void salvar(Despesa despesa) {

		try {

			FileWriter fileWriter = new FileWriter(file, UTF_8,true);
			fileWriter.write(despesa.toFile() + lineSeparator());
			fileWriter.flush();
			fileWriter.close();
			System.out.printf("Despesa %s cadastrada com sucesso.", despesa.getNome());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.printf("Arquivo não encontrado.");
		} catch (IOException e) {
			System.err.println("Erro de IO.");
			e.printStackTrace();
		}

	}

	public Despesa findById(Integer id) {
		// TODO implementação pendente, ainda preciso modificar a classe de modelo com o
		// campo Id
		return null;
	}

	public Optional<Despesa> findByName(String nome) {

		return findAll().filter(d -> d.getNome().equalsIgnoreCase(nome)).findFirst();
	}

	@SuppressWarnings("unused")
	public Stream<Despesa> findAll() {

		Set<Despesa> despesas = new LinkedHashSet<>();

		try (Scanner sc = new Scanner(new File(getFileName()), UTF_8)) {

			while (sc.hasNext()) {

				String linha = sc.nextLine();
				try (Scanner scLinha = new Scanner(linha)) {

					scLinha.useDelimiter(" ; ");

					String nome = scLinha.next();
					String descricao = scLinha.next();
					String categoria = scLinha.next();
					String prioridade = scLinha.next();
					BigDecimal valor = new BigDecimal(scLinha.next());

					Despesa d = new Despesa(nome, descricao, categoria, PODE_ESPERAR, valor);
					despesas.add(d);

				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return despesas.stream();

	}

	public File getFile() {
		return this.file;
	}

	/**
	 * Método auxiliar para os métodos da Classe Dao
	 * 
	 * @return String nome do arquivo de persistência
	 */
	private String getFileName() {
		String fileName = getFileNameWithCurrentDate();
		return fileName;
	}

	/**
	 * Método auxilar para gerar o nome do arquivo de persistência baseado no mês e
	 * ano atual.
	 * 
	 * @return String despesa_Mês_Ano
	 */
	private String getFileNameWithCurrentDate() {
		int mesAtual = LocalDate.now().getMonthValue();
		int anoAtual = LocalDate.now().getYear();

		return String.format("despesas-%d-%d.txt", mesAtual, anoAtual);

	}

}
