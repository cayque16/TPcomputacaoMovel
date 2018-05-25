package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Mamadeiras extends Atividades {

    private float quantidade; //em ml
    private boolean tomouTudo;

    public Mamadeiras(TiposAtivi nome, Date data, Date hora, float quantidade, boolean tomouTudo) {
        super(nome, data, hora);
        this.quantidade = quantidade;
        this.tomouTudo = tomouTudo;
    }

    @Override
    public void atualizaHistorico() {

    }
}
