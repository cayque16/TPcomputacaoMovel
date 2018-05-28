package br.ufop.cayque.mybabycayque.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.models.Fraldas;

/**
 * Created by cayqu on 28/05/2018.
 */

public class FraldasAdapter extends BaseAdapter{

    private ArrayList<Fraldas> list;
    private Context context;

    public FraldasAdapter(ArrayList<Fraldas> list, Context context) {
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

        Fraldas fraldas = list.get(i);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.fralda_adapter, null);

        TextView tv1 = v.findViewById(R.id.adapFraldaData);
        tv1.setText("Data: " + conversor(fraldas.getDiaInicio()) + "/" + conversor(fraldas.getMesInico()) + "/" + conversor(fraldas.getAnoInicio()));

        TextView tv2 = v.findViewById(R.id.adapFraldaHinicio);
        tv2.setText("Hora:" + conversor(fraldas.getHoraInicio()) + ":" + conversor(fraldas.getMinuInicio()) + ":" + conversor(fraldas.getSeguInicio()));

        TextView tv3 = v.findViewById(R.id.adapFraldaMotivo);
        tv3.setText("Motivo: " + fraldas.getMotivo());

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
