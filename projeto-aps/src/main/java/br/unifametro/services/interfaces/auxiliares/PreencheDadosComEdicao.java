package br.unifametro.services.interfaces.auxiliares;

import java.util.Scanner;

public interface PreencheDadosComEdicao<T> extends PreencheDados<T> {

    T getDadosEdicao(Scanner keyboardInput);

}
