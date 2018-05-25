package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Mamadas extends Atividades {

    private String peito;

    public Mamadas(int id, TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int diaTermino, int mesTermino, int anoTermino, String peito) {
        super(id, tipo, diaInicio, mesInico, anoInicio, diaTermino, mesTermino, anoTermino);
        this.peito = peito;
    }

    public String getPeito() {
        return peito;
    }

    public void setPeito(String peito) {
        this.peito = peito;
    }

    @Override
    public void atualizaHistorico() {

    }
}
