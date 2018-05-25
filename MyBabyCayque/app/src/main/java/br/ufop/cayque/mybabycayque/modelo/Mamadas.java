package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Mamadas extends Atividades {

    private String peito;

    public Mamadas(TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int diaTermino, int mesTermino, int anoTermino, int horaTermino, int minuTermino, int seguTermino, String peito) {
        super(tipo, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, diaTermino, mesTermino, anoTermino, horaTermino, minuTermino, seguTermino);
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
