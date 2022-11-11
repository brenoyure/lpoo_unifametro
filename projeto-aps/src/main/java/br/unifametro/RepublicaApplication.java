package br.unifametro;

import java.util.Scanner;

import br.unifametro.menus.Menu;

public class RepublicaApplication {

    public static void main(String[] args) {

        try (Scanner sc = new Scanner(System.in)) {

            Menu menuPrincipal = new Menu(sc);
            menuPrincipal.exibir(sc);
        }

    }

}
