package br.ufop.cayque.mybabycayque.models;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Mamadeiras extends Atividades {

    private float quantidade; //em ml
    private int tomouTudo; //1 se sim e 0 se nao

    public Mamadeiras(TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int diaTermino, int mesTermino, int anoTermino, int horaTermino, int minuTermino, int seguTermino, float quantidade, int tomouTudo) {
        super(tipo, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, diaTermino, mesTermino, anoTermino, horaTermino, minuTermino, seguTermino);
        this.quantidade = quantidade;
        this.tomouTudo = tomouTudo;
    }

    public float getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(float quantidade) {
        this.quantidade = quantidade;
    }

    public int getTomouTudo() {
        return tomouTudo;
    }

    public void setTomouTudo(int tomouTudo) {
        this.tomouTudo = tomouTudo;
    }

    @Override
    public void atualizaHistorico() {

    }
}
