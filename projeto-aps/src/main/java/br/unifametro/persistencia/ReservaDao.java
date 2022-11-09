package br.unifametro.persistencia;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.Locale.US;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Reserva;
import br.unifametro.services.AlunoService;

public class ReservaDao implements Dao<Reserva> {

	private final File file = new File(getFileName());
	private final AlunoService alunoService;

	public ReservaDao(AlunoService alunoService) {
		this.alunoService = alunoService;
	}

	@Override
	public void salvar(Reserva reserva) {
		try (FileWriter fw = new FileWriter(file, UTF_8, true)) {
			fw.write(reserva.toFile() + System.lineSeparator());
			fw.close();
			System.out.println("Reserva cadastrada com sucesso.");

		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Erro ao salvar.");
		}

	}

	@Override
	public void editar(Reserva dadosAntigos, Reserva dadosNovos) {
		// TODO Auto-generated method stub

	}

	@Override
	public void excluir(Reserva reserva) {
		// TODO Auto-generated method stub
	}

	@Override
	public Stream<Reserva> findAll() {
		// TODO o nextInt do Scanner está "pulando" o alunoId
		Set<Reserva> reservas = new LinkedHashSet<>();
		if (fileExists()) {

			try (BufferedReader br = new BufferedReader(new FileReader(file, UTF_8))) {

				String linha = br.readLine();

				try (Scanner scLinha = new Scanner(linha)) {
					scLinha.useDelimiter(" ; ");
					scLinha.useLocale(US);
					while (linha != null) {

						Integer alunoId = Integer.valueOf(scLinha.next());
						Aluno aluno = alunoService.getById(alunoId).get();

						reservas.add(new Reserva(aluno));

					}

				}

			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Erro de IO.");
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

	@SuppressWarnings("unused")
	private Path getFilePath() {
		return Paths.get(file.getPath());
	}

}
