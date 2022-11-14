package br.unifametro.persistencia.interfaces;

public interface DaoEditavel<T> extends Dao<T> {

    void editar(T dadosAntigos, T dadosNovos);

}
