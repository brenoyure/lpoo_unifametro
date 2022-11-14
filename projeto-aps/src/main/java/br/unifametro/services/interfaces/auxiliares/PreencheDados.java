package br.unifametro.services.interfaces.auxiliares;

import java.util.Scanner;

public interface PreencheDados<T> {

    T getDados(Scanner keyboardInput);

}
