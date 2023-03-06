package br.unifametro.services.interfaces.auxiliares;

import java.util.Optional;
import java.util.Scanner;

public interface PreencheDadosComEdicao<T> extends PreencheDados<T> {

	Optional<T> getDadosEdicao(Scanner keyboardInput);

}
