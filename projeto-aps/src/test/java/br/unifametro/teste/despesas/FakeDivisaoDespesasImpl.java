package br.unifametro.teste.despesas;

import static br.unifametro.modelo.Prioridade.MEDIA;
import static java.math.BigDecimal.valueOf;
import static java.math.RoundingMode.HALF_UP;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import br.unifametro.modelo.Aluno;
import br.unifametro.modelo.Despesa;
import br.unifametro.persistencia.interfaces.Dao;

/**
 * Implementação Fake da classe de Divisão de Despesas.
 * @author breno.brito
 *
 */
public class FakeDivisaoDespesasImpl {

	private static Dao<Aluno> alunosDao;
	private static Dao<Despesa> despesasDao;

	@BeforeAll
	static void setUp() {
		alunosDao = new FakeAlunoDaoImpl();
		despesasDao = new FakeDespesasDaoImpl();
	}

	@Test
	void getQuantidadeAlunos() {
		assertEquals(3, calcularQuantidadeAlunos());
	}

	@Test
	void getQuantidadeDespesas() {
		assertEquals(5, calcularQuantidadeDespesas());
	}

	@Test
	void getValorTotalDespesas() {
		assertEquals(valueOf(448.98).setScale(2, HALF_UP), calcularTotalDespesas());
	}

	@Test
	void getDivisaoIgualitaria() {
		assertEquals(valueOf(149.66).setScale(2, HALF_UP), calcularDivisaoIgualitaria());
	}
	
	private BigDecimal calcularDivisaoIgualitaria() {
		return calcularTotalDespesas().divide(valueOf(calcularQuantidadeAlunos()), HALF_UP);
	}
	
	private long calcularQuantidadeAlunos() {
		return alunosDao.findAll().count();
	}
	
	private long calcularQuantidadeDespesas() {
		return despesasDao.findAll().count();
	}
	
	private BigDecimal calcularTotalDespesas() {
		return valueOf(
					despesasDao.findAll().map(Despesa::getValor)
						.mapToDouble(BigDecimal::doubleValue).sum())
							.setScale(2, HALF_UP);
	}
	

}

class FakeDespesasDaoImpl implements Dao<Despesa> {

	private static final List<Despesa> list = new ArrayList<>();

	static {
		list.add(new Despesa("Conta 1", "D", "C1", MEDIA, new BigDecimal("99.99")));
		list.add(new Despesa("Conta 2", "D", "C2", MEDIA, new BigDecimal("120.00")));
		list.add(new Despesa("Conta 3", "D", "C3", MEDIA, new BigDecimal("139.00")));
		list.add(new Despesa("Conta 4", "D", "C4", MEDIA, new BigDecimal("59.99")));
		list.add(new Despesa("Conta 5", "D", "C5", MEDIA, new BigDecimal("30.00")));
	}

	@Override
	public void salvar(Despesa t) {
		//FAKE Implementation
		return;

	}

	@Override
	public void excluir(Despesa t) {
		//FAKE Implementation
		return;

	}

	@Override
	public Stream<Despesa> findAll() {
		return list.stream();
	}

}

class FakeAlunoDaoImpl implements Dao<Aluno> {

	private static final List<Aluno> list = new ArrayList<>();

	static {
		list.add(new Aluno(222, "Breno Yuri", "breno.brito@aluno.unifametro.edu,br", new BigDecimal("1000.00")));
		list.add(new Aluno(444, "Thiago Silva", "thiago.silva@aluno.unifametro.edu,br", new BigDecimal("1000.00")));
		list.add(new Aluno(777, "Inacio Paz", "inacio.paz@aluno.unifametro.edu,br", new BigDecimal("1000.00")));
	}

	@Override
	public void salvar(Aluno t) {
		//FAKE Implementation
		return;

	}

	@Override
	public void excluir(Aluno t) {
		//FAKE Implementation
		return;

	}

	@Override
	public Stream<Aluno> findAll() {
		return list.stream();
	}

}
