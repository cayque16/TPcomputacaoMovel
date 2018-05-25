package br.ufop.cayque.mybabycayque.models;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Outros extends Atividades {
    private String nota;

    public Outros(TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int diaTermino, int mesTermino, int anoTermino, int horaTermino, int minuTermino, int seguTermino, String nota) {
        super(tipo, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, diaTermino, mesTermino, anoTermino, horaTermino, minuTermino, seguTermino);
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
