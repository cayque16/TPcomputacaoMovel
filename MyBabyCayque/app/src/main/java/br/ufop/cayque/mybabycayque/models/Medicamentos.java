package br.ufop.cayque.mybabycayque.models;

import android.content.Context;
import android.os.Parcel;

import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;

/**
 * Created by cayqu on 16/05/2018.
 */

public class Medicamentos extends Atividades {
    public static final int QUATRO_EM_QUATRO = 6;
    public static final int SEIS_EM_SEIS = 4;
    public static final int OITO_EM_OITO = 3;
    public static final int DOZE_EM_DOZE = 2;
    public static final int TODO_DIA = 1;

    private String nome;
    private String unidadeMedi;
    private String dose;
    private int notificacao; //1 sim 0 nao
    private int frequenciaNotifica;

    public Medicamentos(String tipo, int id, int diaInicio, int mesInico, int anoInicio, int horaInicio, int minuInicio, int seguInicio, int duracao, String nome, String unidadeMedi, String dose, String anotacao, int notificacao, int frequenciaNotifica) {
        super(tipo, id, diaInicio, mesInico, anoInicio, horaInicio, minuInicio, seguInicio, duracao, anotacao);
        this.nome = nome;
        this.unidadeMedi = unidadeMedi;
        this.dose = dose;
        this.notificacao = notificacao;
        this.frequenciaNotifica = frequenciaNotifica;
    }

    protected Medicamentos(Parcel in) {
        super(in.readString(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readInt(), in.readString());
        this.nome = in.readString();
        this.unidadeMedi = in.readString();
        this.dose = in.readString();
        this.notificacao = in.readInt();
        this.frequenciaNotifica = in.readInt();
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

    public int getNotificacao() {
        return notificacao;
    }

    public void setNotificacao(int notificacao) {
        this.notificacao = notificacao;
    }

    public int getFrequenciaNotifica() {
        return frequenciaNotifica;
    }

    public void setFrequenciaNotifica(int frequenciaNotifica) {
        this.frequenciaNotifica = frequenciaNotifica;
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
        parcel.writeString(nome);
        parcel.writeString(unidadeMedi);
        parcel.writeString(dose);
        parcel.writeInt(notificacao);
        parcel.writeInt(frequenciaNotifica);
    }
}
