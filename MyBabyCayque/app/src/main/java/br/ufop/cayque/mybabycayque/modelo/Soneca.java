package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Soneca extends Atividades {
    private float horasDormidas;

    public Soneca(int id, TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int diaTermino, int mesTermino, int anoTermino, float horasDormidas) {
        super(id, tipo, diaInicio, mesInico, anoInicio, diaTermino, mesTermino, anoTermino);
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
