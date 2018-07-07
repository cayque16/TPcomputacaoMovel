package br.ufop.cayque.mybabycayque.models;

import android.content.Context;
import android.os.Parcel;

import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Mamadeiras extends Atividades {

    private float quantidade; //em ml
    private int tomouTudo; //1 se sim e 0 se nao

    public Mamadeiras(String tipo, int id, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int duracao, float quantidade, int tomouTudo, String anotacao) {
        super(tipo, id, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, duracao, anotacao);
        this.quantidade = quantidade;
        this.tomouTudo = tomouTudo;
    }

    protected Mamadeiras(Parcel in) {
        super(in.readString(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readString());
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
        parcel.writeFloat(quantidade);
        parcel.writeInt(tomouTudo);
    }
}
