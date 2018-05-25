package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Medicamentos extends Atividades {
    private String nome;
    private String unidadeMedi;
    private String dose;

    public Medicamentos(int id, TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int diaTermino, int mesTermino, int anoTermino, String nome, String unidadeMedi, String dose) {
        super(id, tipo, diaInicio, mesInico, anoInicio, diaTermino, mesTermino, anoTermino);
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
