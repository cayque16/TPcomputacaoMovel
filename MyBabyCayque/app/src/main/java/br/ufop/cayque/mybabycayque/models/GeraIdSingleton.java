package br.ufop.cayque.mybabycayque.models;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by cayqu on 30/05/2018.
 */

public class GeraIdSingleton implements Parcelable, Serializable {
    private static GeraIdSingleton instance;
    private int idAtual = 0;

    private GeraIdSingleton() {
        //construtor vazio
    }

    public static GeraIdSingleton getInstance() {
        if (instance == null) {
            instance = new GeraIdSingleton();
        }
        return instance;
    }

    protected GeraIdSingleton(Parcel in) {
        idAtual = in.readInt();
    }

    public int geraId(Context context){
        idAtual += 1;
        saveGeraId(context);
        return idAtual;
    }

    public static final Creator<GeraIdSingleton> CREATOR = new Creator<GeraIdSingleton>() {
        @Override
        public GeraIdSingleton createFromParcel(Parcel parcel) {
            return new GeraIdSingleton(parcel);
        }

        @Override
        public GeraIdSingleton[] newArray(int i) {
            return new GeraIdSingleton[i];
        }
    };

    public static void saveGeraId(Context context) {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput("geraId.tmp",
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

    public static void loadGeraId(Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput("geraId.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            instance = (GeraIdSingleton) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idAtual);
    }
}
