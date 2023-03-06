package br.unifametro.services.interfaces.auxiliares;

import java.util.Optional;
import java.util.Scanner;

public interface PreencheDados<T> {

    Optional<T> getDados(Scanner keyboardInput);

}
