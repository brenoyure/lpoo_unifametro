package br.unifametro;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.util.Scanner;

import br.unifametro.menus.Menu;

public class RepublicaApplication {

    public static void main(String[] args) {

        try (Scanner keyboardInput = new Scanner(System.in, UTF_8)) {

            Menu menuPrincipal = new Menu();
            menuPrincipal.exibir(keyboardInput);
        }

    }

}
