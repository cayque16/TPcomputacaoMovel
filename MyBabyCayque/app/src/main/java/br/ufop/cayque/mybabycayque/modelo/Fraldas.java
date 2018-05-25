package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Fraldas extends Atividades {
    private String motivo; //xixi ou coco

    public Fraldas(TiposAtivi nome, Date data, Date hora, String motivo) {
        super(nome, data, hora);
        this.motivo = motivo;
    }

    @Override
    public void atualizaHistorico() {

    }
}
