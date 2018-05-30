package br.ufop.cayque.mybabycayque.models;

import android.content.Context;
import android.os.Parcel;

import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Medicamentos extends Atividades {
    private String nome;
    private String unidadeMedi;
    private String dose;

    public Medicamentos(String tipo, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int diaTermino, int mesTermino, int anoTermino, int horaTermino, int minuTermino, int seguTermino, String nome, String unidadeMedi, String dose) {
        super(tipo, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, diaTermino, mesTermino, anoTermino, horaTermino, minuTermino, seguTermino);
        this.nome = nome;
        this.unidadeMedi = unidadeMedi;
        this.dose = dose;
    }

    protected Medicamentos(Parcel in) {
        super(in.readString(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt());
        this.nome = in.readString();
        this.unidadeMedi = in.readString();
        this.dose = in.readString();
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUnidadeMedi() {
        return unidadeMedi;
    }

    public void setUnidadeMedi(String unidadeMedi) {
        this.unidadeMedi = unidadeMedi;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    @Override
    public void atualizaHistorico(Context context) {
        HistoricoSingleton.getInstance().getAtividades().add(this);
        HistoricoSingleton.getInstance().saveAtividades(context);
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
        parcel.writeString(nome);
        parcel.writeString(unidadeMedi);
        parcel.writeString(dose);
    }
}
