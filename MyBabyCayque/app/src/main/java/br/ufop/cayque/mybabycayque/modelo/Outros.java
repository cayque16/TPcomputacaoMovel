package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Outros extends Atividades {
    private String nota;

    public Outros(int id, TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int diaTermino, int mesTermino, int anoTermino, String nota) {
        super(id, tipo, diaInicio, mesInico, anoInicio, diaTermino, mesTermino, anoTermino);
        this.nota = nota;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    @Override
    public void atualizaHistorico() {

    }
}
