package br.ufop.cayque.mybabycayque.models;

import android.content.Context;
import android.os.Parcel;

import java.util.Iterator;

import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Fraldas extends Atividades {
    private String motivo; //xixi ou coco

    public Fraldas(String tipo, int id, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int diaTermino, int mesTermino, int anoTermino, int horaTermino, int minuTermino, int seguTermino, String motivo) {
        super(tipo, id, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, diaTermino, mesTermino, anoTermino, horaTermino, minuTermino, seguTermino);
        this.motivo = motivo;
    }

    protected Fraldas(Parcel in) {
        super(in.readString(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
        this.motivo = in.readString();
    }

    public static final Creator<Fraldas> CREATOR = new Creator<Fraldas>() {
        @Override
        public Fraldas createFromParcel(Parcel parcel) {
            return new Fraldas(parcel);
        }

        @Override
        public Fraldas[] newArray(int i) {
            return new Fraldas[i];
        }
    };

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    @Override
    public void addHistorico(Context context) {
        HistoricoSingleton.getInstance().getAtividades().add(this);
        HistoricoSingleton.getInstance().saveAtividades(context);
    }

    @Override
    public void editHistorico(Context context) {
        int sizeArray = HistoricoSingleton.getInstance().getAtividades().size();
        for (int i = 0; i < sizeArray; i++) {
            if (HistoricoSingleton.getInstance().getAtividades().get(i).getId() == this.getId()){
                HistoricoSingleton.getInstance().getAtividades().set(i,this);
                HistoricoSingleton.getInstance().saveAtividades(context);
                break;
            }
        }
    }

    @Override
    public void removeHistorico(Context context) {
        int sizeArray = HistoricoSingleton.getInstance().getAtividades().size();
        for (int i = 0; i < sizeArray; i++) {
            if (HistoricoSingleton.getInstance().getAtividades().get(i).getId() == this.getId()){
                HistoricoSingleton.getInstance().getAtividades().remove(i);
                HistoricoSingleton.getInstance().saveAtividades(context);
                break;
            }
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(getTipo());
        parcel.writeInt(getId());
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
        parcel.writeString(motivo);
    }
}
