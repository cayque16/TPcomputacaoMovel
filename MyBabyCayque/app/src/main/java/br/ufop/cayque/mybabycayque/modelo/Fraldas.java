package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Fraldas extends Atividades {
    private String motivo; //xixi ou coco

    public Fraldas(int id, TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int diaTermino, int mesTermino, int anoTermino, String motivo) {
        super(id, tipo, diaInicio, mesInico, anoInicio, diaTermino, mesTermino, anoTermino);
        this.motivo = motivo;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public void atualizaHistorico() {

    }
}
