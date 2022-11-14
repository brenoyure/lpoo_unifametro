package br.unifametro.services.interfaces;

import java.util.Scanner;

public interface EditavelService<T> extends Service<T> {

    void editar(Scanner keyboardInput);

}
