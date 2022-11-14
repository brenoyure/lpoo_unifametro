package br.unifametro.persistencia;

import static br.unifametro.modelo.Prioridade.PODE_ESPERAR;
import static java.lang.System.lineSeparator;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Locale.US;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import br.unifametro.modelo.Despesa;
import br.unifametro.persistencia.interfaces.DaoEditavel;

/**
 * Classe responsável pelas operações de IO com o arquivo de persistência.
 * 
 * @see br.unifametro.services.DespesasService
 */
public class DespesasDao implements DaoEditavel<Despesa> {

	private final File file = new File(getFileName());

	@Override
	public void salvar(Despesa despesa) {

		try (FileWriter fileWriter = new FileWriter(file, UTF_8, true)) {
			fileWriter.write(despesa.toFile() + lineSeparator());
			fileWriter.close();
			System.out.printf("Despesa %s cadastrada com sucesso.", despesa.getNome());

		} catch (IOException e) {
			System.err.println("Erro de IO.");
			e.printStackTrace();
		}

	}

	public Optional<Despesa> findByName(String nome) {

		return findAll().filter(d -> d.getNome().equalsIgnoreCase(nome)).findFirst();
	}

	@SuppressWarnings("unused")
	public Stream<Despesa> findAll() {

		Set<Despesa> despesas = new LinkedHashSet<>();

		if (file.exists()) {

			try (Scanner sc = new Scanner(file, UTF_8)) {

				while (sc.hasNext()) {

					String linha = sc.nextLine();
					try (Scanner scLinha = new Scanner(linha)) {

						scLinha.useDelimiter(" ; ");
						scLinha.useLocale(US);

						String nome = scLinha.next();
						String descricao = scLinha.next();
						String categoria = scLinha.next();
						String prioridade = scLinha.next();
						BigDecimal valor = scLinha.nextBigDecimal();

						Despesa d = new Despesa(nome, descricao, categoria, PODE_ESPERAR, valor);
						despesas.add(d);

					}

				}

			} catch (InputMismatchException e) {
				System.err.println("Erro ao carregar algum valor, favor verificar o arquivo de persistência.");
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return despesas.stream();

	}

	@Override
	public void editar(Despesa dadosAntigos, Despesa dadosNovos) {

		if (fileExists()) {

			try (Stream<String> stream = Files.lines(getFilePath(), UTF_8)) {

				// Do the line replace
				List<String> list = stream
						.map(line -> line.startsWith(dadosAntigos.getNome()) ? dadosNovos.toFile() : line)
						.collect(toList());

				// Write the content back
				Files.write(Paths.get(getFileName()), list, UTF_8);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void excluir(Despesa despesa) {

		if (fileExists()) {

			try {

				List<String> list = findAll().filter(d -> !d.equals(despesa)).map(Despesa::toFile).toList();
				Files.write(getFilePath(), list, UTF_8);
				System.out.printf("Despesa de %s excluída com sucesso.", despesa.getNome());

			} catch (IOException e) {
				System.err.println(e.getLocalizedMessage());
			}
		}

	}

	/**
	 * Método auxiliar para os métodos da Classe Dao
	 * 
	 * @return String nome do arquivo de persistência
	 */
	@Override
	public String getFileName() {
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

	@Override
	public boolean fileExists() {
		return file.exists() && file.length() > 0;
	}

	private Path getFilePath() {
		return Paths.get(file.getPath());
	}

}
