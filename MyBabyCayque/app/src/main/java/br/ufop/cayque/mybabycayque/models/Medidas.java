package br.ufop.cayque.mybabycayque.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by cayqu on 28/05/2018.
 */

public class Medidas implements Parcelable, Serializable {

    private int id;
    private int dia, mes, ano;
    private float peso; //em KG
    private int altura; //em cm

    public Medidas(int id, int dia, int mes, int ano, float peso, int altura) {
        this.id = id;
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
        this.peso = peso;
        this.altura = altura;
    }

    protected Medidas(Parcel in){
        this.id = in.readInt();
        this.dia = in.readInt();
        this.mes = in.readInt();
        this.ano = in.readInt();
        this.peso = in.readFloat();
        this.altura = in.readInt();
    }

    public static final Creator<Medidas> CREATOR = new Creator<Medidas>() {
        @Override
        public Medidas createFromParcel(Parcel parcel) {
            return new Medidas(parcel);
        }

        @Override
        public Medidas[] newArray(int i) {
            return new Medidas[i];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(dia);
        parcel.writeInt(mes);
        parcel.writeInt(ano);
        parcel.writeFloat(peso);
        parcel.writeInt(altura);
    }
}