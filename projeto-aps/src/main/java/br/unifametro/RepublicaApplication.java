package br.unifametro;

import java.util.NoSuchElementException;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.unifametro.menus.MenuPrincipal;
import br.unifametro.menus.interfaces.Menu;

@SpringBootApplication
public class RepublicaApplication implements CommandLineRunner {

	private final Scanner keyboardInput;
	private final Menu menuPrincipal;

	/**
	 * Classe responsável por iniciar o aplicativo.
	 * 
	 * @param keyboardInput entrada via teclado para interação do usuário.
	 * @param menuPrincipal instancia do Menu Principal do Aplicativo.
	 */
	@Autowired
	public RepublicaApplication(MenuPrincipal menuPrincipal) {
		this.keyboardInput = new Scanner(System.in);
		this.menuPrincipal = menuPrincipal;
	}

	public static void main(String[] args) {
		SpringApplication.run(RepublicaApplication.class, args);

	}

	@Override
	public void run(String... args) {

		try {
			this.menuPrincipal.exibirMenu(keyboardInput);
			System.out.printf("\nSaindo...Obrigado por utilizar.\n");
			
		} catch (NoSuchElementException e) {
			System.err.println("\nSaída abrupta do aplicativo detectada.");
			System.err.println("Você pressionou 'Control + C', ou ocorreu algum erro na leitura de algum registro do txt.\n");
		}

	}

}
