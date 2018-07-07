package br.ufop.cayque.mybabycayque.models;

import android.content.Context;
import android.os.Parcel;

import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Outros extends Atividades {

    public Outros(String tipo, int id, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int duracao, String anotacao) {
        super(tipo, id, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, duracao, anotacao);
    }

    protected Outros(Parcel in) {
        super(in.readString(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(),in.readInt(), in.readString());
    }

    public static final Creator<Outros> CREATOR = new Creator<Outros>() {
        @Override
        public Outros createFromParcel(Parcel parcel) {
            return new Outros(parcel);
        }

        @Override
        public Outros[] newArray(int i) {
            return new Outros[i];
        }
    };


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
    }
}
