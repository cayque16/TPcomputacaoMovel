package br.ufop.cayque.mybabycayque.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.models.Sonecas;

/**
 * Created by cayqu on 28/05/2018.
 */

public class SonecasAdapter extends BaseAdapter {
    private ArrayList<Sonecas> list;
    private Context context;

    public SonecasAdapter(ArrayList<Sonecas> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Sonecas sonecas = list.get(i);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.soneca_adapter, null);

        TextView tv1 = v.findViewById(R.id.adapSonecaDataI);
        tv1.setText("Data: " + conversor(sonecas.getDiaInicio()) + "/" + conversor(sonecas.getMesInico()) + "/" + conversor(sonecas.getAnoInicio()));

        TextView tv2 = v.findViewById(R.id.adapSonecaDataF);
        tv2.setText(" até " + conversor(sonecas.getDiaTermino()) + "/" + conversor(sonecas.getMesTermino()) + "/" + conversor(sonecas.getAnoTermino()));

        TextView tv3 = v.findViewById(R.id.adapSonecaHinicio);
        tv3.setText("De: " + conversor(sonecas.getHoraInicio()) + ":" + conversor(sonecas.getMinuInicio()) + ":" + conversor(sonecas.getSeguInicio()));

        TextView tv4 = v.findViewById(R.id.adapSonecaHtermino);
        tv4.setText(" até " + conversor(sonecas.getHoraTermino()) + ":" + conversor(sonecas.getMinuTermino()) + ":" + conversor(sonecas.getSeguTermino()));

        return v;
    }

    public String conversor(int valor) {
        String temp = "0";
        if (valor < 10) {
            return temp + valor;
        }
        return Integer.toString(valor);
    }
}
