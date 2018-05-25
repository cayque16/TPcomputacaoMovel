package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public abstract class Atividades {
    private int id;
    private TiposAtivi tipo;
    private Date data,hora;
    private static int idAtual = 0;

    public Atividades(TiposAtivi nome, Date data, Date hora) {
        idAtual++;
        this.id = idAtual;
        this.tipo = nome;
        this.data = data;
        this.hora = hora;
    }

    public int getId() {
        return id;
    }

    public TiposAtivi getNome() {
        return tipo;
    }

    public void setNome(TiposAtivi nome) {
        this.tipo = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public static int getIdAtual() {
        return idAtual;
    }

    public abstract void atualizaHistorico();
}
