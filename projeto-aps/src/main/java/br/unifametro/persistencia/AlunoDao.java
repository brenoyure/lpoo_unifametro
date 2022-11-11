package br.unifametro.persistencia;

import static java.lang.System.lineSeparator;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Locale.US;
import static java.util.stream.Collectors.toList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import br.unifametro.modelo.Aluno;

/**
 * Classe responsável pelas operações de IO com o arquivo de persistência.
 * 
 * @see br.unifametro.services.AlunoService
 */
public class AlunoDao implements Dao<Aluno> {

	private static File file = new File("alunos.txt");

	public AlunoDao() {

	}

	public void salvar(Aluno aluno) {

		try (FileWriter fileWriter = new FileWriter(file, UTF_8, true)) {
			fileWriter.write(aluno.toFile() + lineSeparator());
			System.out.printf("Aluno %s cadastrado com sucesso.", aluno.getNome());

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Erro de IO.");
		}

	}

	public Optional<Aluno> findById(Integer id) {

		return findAll().filter(a -> a.getId().equals(id)).findFirst();

	}

	public Optional<Aluno> findByName(String nome) {

		return findAll().filter(a -> a.getNome().contains(nome)).findFirst();

	}

	public Stream<Aluno> findAll() {

		Set<Aluno> alunos = new LinkedHashSet<>();

		if (file.exists()) {

			try (Scanner sc = new Scanner(file, UTF_8)) {

				while (sc.hasNext()) {

					String linha = sc.nextLine();
					try (Scanner scLinha = new Scanner(linha)) {

						scLinha.useDelimiter(" ; ");
						scLinha.useLocale(US);

						Integer id = scLinha.nextInt();
						String nome = scLinha.next();
						String email = scLinha.next();
						BigDecimal rend = scLinha.nextBigDecimal();

						Aluno a = new Aluno(id, nome, email, rend);
						alunos.add(a);

					}

				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return alunos.stream();

	}

	public void editar(Aluno dadosAntigos, Aluno dadosNovos) {

		try (Stream<String> stream = Files.lines(getFilePath())) {

			// Do the line replace
			List<String> list = stream.map(line -> line.equals(dadosAntigos.toFile()) ? dadosNovos.toFile() : line)
					.collect(toList());

			// Write the content back
			Files.write(getFilePath(), list, UTF_8);

		} catch (NoSuchFileException e) {
			System.err.println(e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void excluir(Aluno aluno) {

		try (Stream<String> lines = Files.lines(getFilePath(), UTF_8)) {
			List<String> list = lines.filter(l -> !l.equalsIgnoreCase(aluno.toFile())).toList();
			Files.write(getFilePath(), list, UTF_8);
		} catch (IOException e) {
			System.err.println(e.getLocalizedMessage());
		}

	}

	/**
	 * Método auxiliar para os métodos da Classe Dao
	 * 
	 * @return String nome do arquivo de persistência
	 */
	@Override
	public String getFileName() {
		return file.getName();
	}

	@Override
	public boolean fileExists() {
		return file.exists() && file.length() > 0;
	}

	private Path getFilePath() {
		return Paths.get(file.getPath());
	}

}
