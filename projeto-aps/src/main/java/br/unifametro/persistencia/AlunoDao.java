package br.unifametro.persistencia;

import static java.lang.System.lineSeparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import br.unifametro.modelo.Aluno;

/**
 * Classe responsável pelas operações de IO com o arquivo de persistência.
 * @see br.unifametro.services.AlunoService
 */
public class AlunoDao {

	private static File file = new File("alunos.txt");

	public AlunoDao() {

	}

	public void salvar(Aluno aluno) {

		try {

			FileWriter fileWriter = new FileWriter(file, true);
			fileWriter.write(aluno.toFile() + lineSeparator());
			fileWriter.flush();
			fileWriter.close();
			System.out.printf("Aluno %s cadastrado com sucesso.", aluno.getNome());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.err.printf("Arquivo não encontrado.");
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Erro de IO.");
		}

	}

	public Aluno findById(Integer id) {

		try {

			return findAll().filter(a -> a.getId().equals(id)).findFirst().orElseThrow();

		} catch (NoSuchElementException e) {
			
			System.err.println("Aluno não encontrado.");
		}

		return null;

	}

	public Aluno findByName(String nome) {

		try {

			return findAll().filter(a -> a.getNome().contains(nome)).findFirst().orElseThrow();

		} catch (NoSuchElementException e) {
			
			System.err.println("Aluno não encontrado.");
		}

		return null;

	}

	public Stream<Aluno> findAll() {

		Set<Aluno> alunos = new LinkedHashSet<>();

		try (Scanner sc = new Scanner(new File(getFileName()), StandardCharsets.UTF_8)) {

			while (sc.hasNext()) {

				String linha = sc.nextLine();
				try (Scanner scLinha = new Scanner(linha)) {

					scLinha.useDelimiter(" ; ");

					Integer id = Integer.valueOf(scLinha.next());
					String nome = scLinha.next();
					String email = scLinha.next();
					BigDecimal rend = scLinha.nextBigDecimal();

					Aluno a = new Aluno(id, nome, email, rend);
					alunos.add(a);

				} catch (Exception e) {
					throw new RuntimeException(e);
				}

			}

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		return alunos.stream();

	}

	public void editar(Aluno dadosAntigos, Aluno dadosNovos) throws IOException {

		try (Stream<String> stream = Files.lines(Paths.get(getFileName()))) {

			// Do the line replace
			List<String> list = stream.map(line -> line.equals(dadosAntigos.toFile()) ? dadosNovos.toFile() : line)
					.collect(Collectors.toList());

			// Write the content back
			Files.write(Paths.get(getFileName()), list);

		}

	}

	/**
	 * Método auxiliar para os métodos da Classe Dao
	 * @return String nome do arquivo de persistência
	 */
	private String getFileName() {
		return file.getName();
	}

}
