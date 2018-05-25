package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Soneca extends Atividades {
    private float horasDormidas;

    public Soneca(TiposAtivi nome, Date data, Date hora, float horasDormidas) {
        super(nome, data, hora);
        this.horasDormidas = horasDormidas;
    }

    @Override
    public void atualizaHistorico() {

    }
}
