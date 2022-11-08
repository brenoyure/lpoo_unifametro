package br.unifametro.persistencia;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

import br.unifametro.modelo.Reserva;

public class ReservaDao implements Dao<Reserva> {

	private final File file = new File(getFileName());

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
		// TODO Auto-generated method stub
		return null;
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
