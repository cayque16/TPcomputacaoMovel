package br.ufop.cayque.mybabycayque.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Mamadas extends Atividades {

    private String peito;

    public Mamadas(String tipo, int id, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int duracao, String peito, String anotacao) {
        super(tipo, id, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, duracao, anotacao);
        this.peito = peito;
    }

    protected Mamadas(Parcel in) {
        super(in.readString(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readString());
        this.peito = in.readString();
    }

    public static final Creator<Mamadas> CREATOR = new Creator<Mamadas>() {
        @Override
        public Mamadas createFromParcel(Parcel parcel) {
            return new Mamadas(parcel);
        }

        @Override
        public Mamadas[] newArray(int i) {
            return new Mamadas[i];
        }
    };

    public String getPeito() {
        return peito;
    }

    public void setPeito(String peito) {
        this.peito = peito;
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
            if (HistoricoSingleton.getInstance().getAtividades().get(i).getId() == this.getId()) {
                HistoricoSingleton.getInstance().getAtividades().set(i, this);
                HistoricoSingleton.getInstance().saveAtividades(context);
                break;
            }
        }
    }

    @Override
    public void removeHistorico(Context context) {
        int sizeArray = HistoricoSingleton.getInstance().getAtividades().size();
        for (int i = 0; i < sizeArray; i++) {
            if (HistoricoSingleton.getInstance().getAtividades().get(i).getId() == this.getId()) {
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
        parcel.writeInt(getDuracao());
        parcel.writeString(getAnotacao());
        parcel.writeString(peito);
    }
}
