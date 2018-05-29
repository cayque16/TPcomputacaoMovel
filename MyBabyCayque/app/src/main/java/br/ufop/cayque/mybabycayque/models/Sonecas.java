package br.ufop.cayque.mybabycayque.models;

import android.os.Parcel;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Sonecas extends Atividades {
    private float horasDormidas;

    public Sonecas(String tipo, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int diaTermino, int mesTermino, int anoTermino, int horaTermino, int minuTermino, int seguTermino, float horasDormidas) {
        super(tipo, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, diaTermino, mesTermino, anoTermino, horaTermino, minuTermino, seguTermino);
        this.horasDormidas = horasDormidas;
    }

    protected Sonecas(Parcel in) {
        super(in.readString(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
        this.horasDormidas = in.readFloat();
    }

    public static final Creator<Sonecas> CREATOR = new Creator<Sonecas>() {
        @Override
        public Sonecas createFromParcel(Parcel parcel) {
            return new Sonecas(parcel);
        }

        @Override
        public Sonecas[] newArray(int i) {
            return new Sonecas[i];
        }
    };

    public float getHorasDormidas() {
        return horasDormidas;
    }

    public void setHorasDormidas(float horasDormidas) {
        this.horasDormidas = horasDormidas;
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
        parcel.writeFloat(horasDormidas);
    }
}
