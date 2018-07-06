package br.ufop.cayque.mybabycayque.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.models.Outros;

/**
 * Created by cayqu on 29/05/2018.
 */

public class OutrosAdapter extends BaseAdapter {

    private ArrayList<Outros> list;
    private Context context;

    public OutrosAdapter(ArrayList<Outros> list, Context context) {
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

        Outros outros = list.get(i);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.outros_adapter, null);

        TextView tv1 = v.findViewById(R.id.adapOutroData);
        tv1.setText("Data: " + conversor(outros.getDiaInicio()) + "/" + conversor(outros.getMesInico()) + "/" + conversor(outros.getAnoInicio()));

        TextView tv2 = v.findViewById(R.id.adapOutroHinicio);
        tv2.setText("Hora: " + conversor(outros.getHoraInicio()) + ":" + conversor(outros.getMinuInicio()));

        TextView tv3 = v.findViewById(R.id.adapOutroNota);
        tv3.setText("Nota: " + outros.getAnotacao());

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

