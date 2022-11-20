package br.unifametro.services.interfaces.auxiliares;

public interface ValidacaoDadosEditaveis<T> extends ValidacaoDados<T> {

    T validarEdicao(T t);

}
