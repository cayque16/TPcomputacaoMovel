package br.ufop.cayque.mybabycayque.models;

import android.content.Context;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by cayqu on 16/05/2018.
 */

public abstract class Atividades implements Parcelable, Serializable {
    private String tipo;
    private int id;
    private int diaInicio, mesInico, anoInicio;
    private int horaInicio, minuInicio, seguInicio;
    private int diaTermino, mesTermino, anoTermino;
    private int horaTermino, minuTermino, seguTermino;

    public Atividades(String tipo, int id, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int diaTermino, int mesTermino, int anoTermino, int horaTermino, int minuTermino, int seguTermino) {
        this.id = id;
        this.tipo = tipo;
        this.diaInicio = diaInicio;
        this.mesInico = mesInico;
        this.anoInicio = anoInicio;
        this.horaInicio = horaInicio;
        this.minuInicio = minuInicio;
        this.seguInicio = seguInicio;
        this.diaTermino = diaTermino;
        this.mesTermino = mesTermino;
        this.anoTermino = anoTermino;
        this.horaTermino = horaTermino;
        this.minuTermino = minuTermino;
        this.seguTermino = seguTermino;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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

    public int getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(int horaInicio) {
        this.horaInicio = horaInicio;
    }

    public int getMinuInicio() {
        return minuInicio;
    }

    public void setMinuInicio(int minuInicio) {
        this.minuInicio = minuInicio;
    }

    public int getSeguInicio() {
        return seguInicio;
    }

    public void setSeguInicio(int seguInicio) {
        this.seguInicio = seguInicio;
    }

    public int getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(int horaTermino) {
        this.horaTermino = horaTermino;
    }

    public int getMinuTermino() {
        return minuTermino;
    }

    public void setMinuTermino(int minuTermino) {
        this.minuTermino = minuTermino;
    }

    public int getSeguTermino() {
        return seguTermino;
    }

    public void setSeguTermino(int seguTermino) {
        this.seguTermino = seguTermino;
    }

    public abstract void addHistorico(Context context);

    public abstract void editHistorico(Context context);

    public abstract void removeHistorico(Context context);

}
