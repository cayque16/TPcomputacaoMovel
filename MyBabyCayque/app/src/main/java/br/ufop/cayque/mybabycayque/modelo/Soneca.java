package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Soneca extends Atividades {
    private float horasDormidas;

    public Soneca(TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int diaTermino, int mesTermino, int anoTermino, int horaTermino, int minuTermino, int seguTermino, float horasDormidas) {
        super(tipo, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, diaTermino, mesTermino, anoTermino, horaTermino, minuTermino, seguTermino);
        this.horasDormidas = horasDormidas;
    }

    public float getHorasDormidas() {
        return horasDormidas;
    }

    public void setHorasDormidas(float horasDormidas) {
        this.horasDormidas = horasDormidas;
    }

    @Override
    public void atualizaHistorico() {

    }
}
