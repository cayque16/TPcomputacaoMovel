package br.ufop.cayque.mybabycayque.models;

import android.os.Parcel;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Mamadeiras extends Atividades {

    private float quantidade; //em ml
    private int tomouTudo; //1 se sim e 0 se nao

    public Mamadeiras(String tipo, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int diaTermino, int mesTermino, int anoTermino, int horaTermino, int minuTermino, int seguTermino, float quantidade, int tomouTudo) {
        super(tipo, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, diaTermino, mesTermino, anoTermino, horaTermino, minuTermino, seguTermino);
        this.quantidade = quantidade;
        this.tomouTudo = tomouTudo;
    }

    protected Mamadeiras(Parcel in) {
        super(in.readString(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
        this.quantidade = in.readFloat();
        this.tomouTudo = in.readInt();
    }

    public static final Creator<Mamadeiras> CREATOR = new Creator<Mamadeiras>() {
        @Override
        public Mamadeiras createFromParcel(Parcel parcel) {
            return new Mamadeiras(parcel);
        }

        @Override
        public Mamadeiras[] newArray(int i) {
            return new Mamadeiras[i];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getTipo());
        parcel.writeInt(getDiaInicio());
        parcel.writeInt(getMesInico());
        parcel.writeInt(getAnoInicio());
        parcel.writeInt(getHoraInicio());
        parcel.writeInt(getMinuInicio());
        parcel.writeInt(getSeguInicio());
        parcel.writeInt(getDiaTermino());
        parcel.writeInt(getMesTermino());
        parcel.writeInt(getAnoTermino());
        parcel.writeInt(getHoraTermino());
        parcel.writeInt(getMinuTermino());
        parcel.writeInt(getSeguTermino());
        parcel.writeFloat(quantidade);
        parcel.writeInt(tomouTudo);
    }
}
