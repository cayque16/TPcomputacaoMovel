package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Outros extends Atividades {
    private String nota;

    public Outros(TiposAtivi nome, Date data, Date hora, String nota) {
        super(nome, data, hora);
        this.nota = nota;
    }

    @Override
    public void atualizaHistorico() {

    }
}
