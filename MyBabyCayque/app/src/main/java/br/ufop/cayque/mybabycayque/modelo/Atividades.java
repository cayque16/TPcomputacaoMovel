package br.ufop.cayque.mybabycayque.modelo;

import java.util.Date;

/**
 * Created by cayqu on 16/05/2018.
 */

public abstract class Atividades {
    private int id;
    private TiposAtivi tipo;
    private int diaInicio,mesInico,anoInicio;
    private int diaTermino,mesTermino,anoTermino;
    private static int idAtual = 0;

    public Atividades(int id, TiposAtivi tipo, int diaInicio, int mesInico, int anoInicio, int diaTermino, int mesTermino, int anoTermino) {
        this.id = id;
        this.tipo = tipo;
        this.diaInicio = diaInicio;
        this.mesInico = mesInico;
        this.anoInicio = anoInicio;
        this.diaTermino = diaTermino;
        this.mesTermino = mesTermino;
        this.anoTermino = anoTermino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TiposAtivi getTipo() {
        return tipo;
    }

    public void setTipo(TiposAtivi tipo) {
        this.tipo = tipo;
    }

    public int getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(int diaInicio) {
        this.diaInicio = diaInicio;
    }

    public int getMesInico() {
        return mesInico;
    }

    public void setMesInico(int mesInico) {
        this.mesInico = mesInico;
    }

    public int getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(int anoInicio) {
        this.anoInicio = anoInicio;
    }

    public int getDiaTermino() {
        return diaTermino;
    }

    public void setDiaTermino(int diaTermino) {
        this.diaTermino = diaTermino;
    }

    public int getMesTermino() {
        return mesTermino;
    }

    public void setMesTermino(int mesTermino) {
        this.mesTermino = mesTermino;
    }

    public int getAnoTermino() {
        return anoTermino;
    }

    public void setAnoTermino(int anoTermino) {
        this.anoTermino = anoTermino;
    }

    public static int getIdAtual() {
        return idAtual;
    }

    public static void setIdAtual(int idAtual) {
        Atividades.idAtual = idAtual;
    }

    public abstract void atualizaHistorico();
}
