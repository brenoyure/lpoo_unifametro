package br.unifametro.persistencia;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.interfaces.Dao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.interfaces.Service;

public class ReservaDao implements Dao<Reserva> {

	private final File file = new File(getFileName());
	private final AlunoService alunoService;

	public ReservaDao(Service<Aluno> alunoService) {
		this.alunoService = (AlunoService) alunoService;
	}

	@Override
	public void salvar(Reserva reserva) {
		try (FileWriter fw = new FileWriter(file, UTF_8, true)) {
			fw.write(reserva.toFile() + System.lineSeparator());
			fw.close();
			System.out.printf("\nReserva do aluno %s cadastrada com sucesso.", reserva.getAluno().getNome());
			System.out.printf("\n%s\n", reserva);

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Erro ao salvar.");
		}

	}

	@Override
	public void excluir(Reserva reserva) {
		if (fileExists()) {

			try {

				List<String> list = findAll().filter(r -> !r.equals(reserva)).map(Reserva::toFile).toList();
				Files.write(getFilePath(), list, UTF_8);
				System.out.printf("\nA reserva '%s' foi excluída com sucesso. ", reserva);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Erro ao Ler o arquivo.");
			}

		}

	}

	@Override
	public Stream<Reserva> findAll() {

		List<Reserva> reservas = new ArrayList<>();

		if (fileExists()) {

			try (Scanner sc = new Scanner(file, UTF_8)) {
				sc.useDelimiter(" ; ");

				while (sc.hasNext()) {

					Aluno aluno = alunoService.getById(sc.nextInt()).get();
					Reserva reserva = new Reserva(aluno);
					reservas.add(reserva);

					sc.nextLine();

				}

			} catch (InputMismatchException e) {
				System.err.println(
						"Possível erro nos campos numéricos, favor verificar no txt de reservas, se a matrícula de algum aluno está incorreta.");

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		return reservas.stream();

	}

	@Override
	public String getFileName() {
		return getFileNameWithCurrentDate();
	}

	@Override
	public boolean fileExists() {
		return file.exists() && file.length() > 0;
	}

	/**
	 * Método auxilar para gerar o nome do arquivo de persistência baseado no mês e
	 * ano atual.
	 * 
	 * @return String reservas_Mês_Ano
	 */
	private String getFileNameWithCurrentDate() {
		int mesAtual = LocalDate.now().getMonthValue();
		int anoAtual = LocalDate.now().getYear();

		return String.format("reservas-%d-%d.txt", mesAtual, anoAtual);

	}

	private Path getFilePath() {
		return Paths.get(file.getPath());
	}

}
