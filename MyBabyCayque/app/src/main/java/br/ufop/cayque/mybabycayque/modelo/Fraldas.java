package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Fraldas extends Atividades {
    private String motivo; //xixi ou coco

    public Fraldas(TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int diaTermino, int mesTermino, int anoTermino, int horaTermino, int minuTermino, int seguTermino, String motivo) {
        super(tipo, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, diaTermino, mesTermino, anoTermino, horaTermino, minuTermino, seguTermino);
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
