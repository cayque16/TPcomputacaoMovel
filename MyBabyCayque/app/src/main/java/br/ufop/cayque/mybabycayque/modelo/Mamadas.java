package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Mamadas extends Atividades {

    private String peito;

    public Mamadas(TiposAtivi nome, Date data, Date hora, String peito) {
        super(nome, data, hora);
        this.peito = peito;
    }

    @Override
    public void atualizaHistorico() {

    }
}
