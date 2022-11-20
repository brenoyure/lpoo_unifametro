package br.unifametro;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.NoSuchElementException;
import java.util.Scanner;

import br.unifametro.menus.Menu;

public final class RepublicaApplication {

    private final Scanner keyboardInput;
    private final Menu menuPrincipal;

    /**
     * Classe responsável por iniciar o aplicativo.
     * 
     * @param keyboardInput entrada via teclado para interação do usuário.
     * @param menuPrincipal	instancia do Menu Principal do Aplicativo.
     */
    public RepublicaApplication(Scanner keyboardInput, Menu menuPrincipal) {
        this.keyboardInput = keyboardInput;
        this.menuPrincipal = menuPrincipal;
    }
    
    private void run() {
        this.menuPrincipal.exibir(keyboardInput);
    }

    public static void main(String[] args) {

        Menu menuPrincipal = new Menu();

        try (Scanner keyboardInput = new Scanner(System.in, UTF_8)) {
            RepublicaApplication app = new RepublicaApplication(keyboardInput, menuPrincipal);
            app.run();
            System.out.printf("\nSaindo...Obrigado por utilizar.\n");

        } catch (NoSuchElementException e) {
            System.err.println("\nSaída abrupta do aplicativo detectada.");
            System.err.println("Você pressionou 'Control + C', ou ocorreu algum erro na leitura de algum registro do txt.\n");
        }

    }

}
