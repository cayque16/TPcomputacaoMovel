package br.ufop.cayque.mybabycayque.modelo;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import br.ufop.cayque.mybabycayque.MainActivity;

/**
 * Created by cayqu on 16/05/2018.
 */

public class DadosBebe implements Parcelable,Serializable {
    private int bebeNull = 1;
    private String nome;
    private int diaNasc,mesNasc,anoNasc;
    private String sexo;
    private static DadosBebe instance;

    private DadosBebe() {
    } //construtor vazio

    public static DadosBebe getInstance() {
        if (instance == null) {
            instance = new DadosBebe();
        }
        return instance;
    }

    protected DadosBebe(Parcel in){
        bebeNull = in.readInt();
        nome = in.readString();
        diaNasc = in.readInt();
        mesNasc = in.readInt();
        anoNasc = in.readInt();
        sexo = in.readString();
    }

    public static final Creator<DadosBebe> CREATOR = new Creator<DadosBebe>() {
        @Override
        public DadosBebe createFromParcel(Parcel parcel) {
            return new DadosBebe(parcel);
        }

        @Override
        public DadosBebe[] newArray(int i) {
            return new DadosBebe[i];
        }
    };

    public static void saveBebe(Context context) {
        FileOutputStream fos;
        instance.bebeNull = 0;
        try {
            fos = context.openFileOutput("bebeSalvo.tmp",
                    context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(instance);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadBebe(Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput("bebeSalvo.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            instance = (DadosBebe) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getDiaNasc() {
        return diaNasc;
    }

    public void setDiaNasc(int diaNasc) {
        this.diaNasc = diaNasc;
    }

    public int getMesNasc() {
        return mesNasc;
    }

    public void setMesNasc(int mesNasc) {
        this.mesNasc = mesNasc;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getBebeNull() {
        return bebeNull;
    }

    public void setBebeNull(int bebeNull) {
        this.bebeNull = bebeNull;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(bebeNull);
        parcel.writeString(nome);
        parcel.writeInt(diaNasc);
        parcel.writeInt(mesNasc);
        parcel.writeInt(anoNasc);
        parcel.writeString(sexo);
    }
}
