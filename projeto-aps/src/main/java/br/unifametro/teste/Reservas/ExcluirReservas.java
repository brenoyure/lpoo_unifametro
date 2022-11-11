package br.unifametro.teste.Reservas;

import java.util.Scanner;

import br.unifametro.modelo.Reserva;
import br.unifametro.persistencia.AlunoDao;
import br.unifametro.persistencia.Dao;
import br.unifametro.persistencia.ReservaDao;
import br.unifametro.services.AlunoService;
import br.unifametro.services.ReservasService;
import br.unifametro.services.Service;

public class ExcluirReservas {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        AlunoDao alunoDao = new AlunoDao();
        AlunoService alunoService = new AlunoService(alunoDao);
        Dao<Reserva> reservaDao = new ReservaDao(alunoService);

        Service<Reserva> service = new ReservasService(reservaDao, alunoService);

        service.excluir(sc);

        sc.close();
    }

}
