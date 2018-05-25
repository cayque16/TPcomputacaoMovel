package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Medicamentos extends Atividades {
    private String nome;
    private String unidadeMedi;
    private String dose;

    public Medicamentos(TiposAtivi nome, Date data, Date hora, String nomeR, String unidadeMedi, String dose) {
        super(nome, data, hora);
        this.nome = nomeR;
        this.unidadeMedi = unidadeMedi;
        this.dose = dose;
    }

    @Override
    public void atualizaHistorico() {

    }
}
