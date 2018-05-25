package br.ufop.cayque.mybabycayque.models;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Medicamentos extends Atividades {
    private String nome;
    private String unidadeMedi;
    private String dose;

    public Medicamentos(TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int diaTermino, int mesTermino, int anoTermino, int horaTermino, int minuTermino, int seguTermino, String nome, String unidadeMedi, String dose) {
        super(tipo, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, diaTermino, mesTermino, anoTermino, horaTermino, minuTermino, seguTermino);
        this.nome = nome;
        this.unidadeMedi = unidadeMedi;
        this.dose = dose;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeMedi() {
        return unidadeMedi;
    }

    public void setUnidadeMedi(String unidadeMedi) {
        this.unidadeMedi = unidadeMedi;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    @Override
    public void atualizaHistorico() {

    }
}
