package br.ufop.cayque.mybabycayque.controllers;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.models.Atividades;
import br.ufop.cayque.mybabycayque.models.Fraldas;
import br.ufop.cayque.mybabycayque.models.Mamadas;
import br.ufop.cayque.mybabycayque.models.Mamadeiras;
import br.ufop.cayque.mybabycayque.models.Medicamentos;
import br.ufop.cayque.mybabycayque.models.Outros;
import br.ufop.cayque.mybabycayque.models.Soneca;

/**
 * Created by cayqu on 16/05/2018.
 */

public class HistoricoSingleton {
    private static HistoricoSingleton historico = null;

    private static ArrayList<Atividades> atividades;
    private static ArrayList<Fraldas> fraldas;
    private static ArrayList<Mamadas> mamadas;
    private static ArrayList<Mamadeiras> mamadeiras;
    private static ArrayList<Medicamentos> medicamentos;
    private static ArrayList<Outros> outros;
    private static ArrayList<Soneca> sonecas;

    private HistoricoSingleton() {
        atividades = new ArrayList<Atividades>();
        fraldas = new ArrayList<Fraldas>();
        mamadas = new ArrayList<Mamadas>();
        mamadeiras = new ArrayList<Mamadeiras>();
        medicamentos = new ArrayList<Medicamentos>();
        outros = new ArrayList<Outros>();
        sonecas = new ArrayList<Soneca>();
    }

    public static HistoricoSingleton getInstance() {
        if (historico == null) {
            historico = new HistoricoSingleton();
        }
        return historico;
    }

    public ArrayList<Atividades> getAtividades() {
        return atividades;
    }

    public ArrayList<Fraldas> getFraldas() {
        return fraldas;
    }

    public ArrayList<Mamadas> getMamadas() {
        return mamadas;
    }

    public ArrayList<Mamadeiras> getMamadeiras() {
        return mamadeiras;
    }

    public ArrayList<Medicamentos> getMedicamentos() {
        return medicamentos;
    }

    public ArrayList<Outros> getOutros() {
        return outros;
    }

    public ArrayList<Soneca> getSonecas() {
        return sonecas;
    }


    public void saveMamadas(Context context) {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput("mamadas.tmp",
                    Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mamadas);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveMamadeiras(Context context) {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput("mamadeiras1.tmp",
                    Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(mamadeiras);
            oos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMamadas(Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput("mamadas.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            mamadas = (ArrayList<Mamadas>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadAtividades(Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput("atividades.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            atividades = (ArrayList<Atividades>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadFraldas(Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput("fraldas.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            fraldas = (ArrayList<Fraldas>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void loadMamadeiras(Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput("mamadeiras1.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            mamadeiras = (ArrayList<Mamadeiras>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadMedicamentos(Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput("medicamentos.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            medicamentos = (ArrayList<Medicamentos>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadOutros(Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput("outros.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            outros = (ArrayList<Outros>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void loadSoneca(Context context) {
        FileInputStream fis;
        try {
            fis = context.openFileInput("sonecas.tmp");
            ObjectInputStream ois = new ObjectInputStream(fis);
            sonecas = (ArrayList<Soneca>) ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}