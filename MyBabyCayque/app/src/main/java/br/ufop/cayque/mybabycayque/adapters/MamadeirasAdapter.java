package br.ufop.cayque.mybabycayque.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.models.Mamadeiras;

/**
 * Created by cayqu on 28/05/2018.
 */

public class MamadeirasAdapter extends BaseAdapter {
    private ArrayList<Mamadeiras> list;
    private Context context;

    public MamadeirasAdapter(ArrayList<Mamadeiras> list, Context context) {
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

        Mamadeiras mamadeiras = list.get(i);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.mamadeira_adapter, null);

        TextView tv1 = v.findViewById(R.id.adapMamadeiraData);
        tv1.setText("Data: " + conversor(mamadeiras.getDiaInicio()) + "/" + conversor(mamadeiras.getMesInico()) + "/" + conversor(mamadeiras.getAnoInicio()));

        TextView tv2 = v.findViewById(R.id.adapMamadeiraHinicio);
        tv2.setText("De " + conversor(mamadeiras.getHoraInicio()) + ":" + conversor(mamadeiras.getMinuInicio()) + ":" + conversor(mamadeiras.getSeguInicio()));

        TextView tv3 = v.findViewById(R.id.adapMamadeiraHtermino);
        tv3.setText(" até " + conversor(mamadeiras.getHoraTermino()) + ":" + conversor(mamadeiras.getMinuTermino()) + ":" + conversor(mamadeiras.getSeguTermino()));

        TextView tv4 = v.findViewById(R.id.adapMamadeiraTomou);
        if (mamadeiras.getTomouTudo() == 1) {
            tv4.setText("Tomou tudo: sim");
        } else {
            tv4.setText("Tomou tudo: não");
        }

        TextView tv5 = v.findViewById(R.id.adapMamadeiraQuanti);
        tv5.setText("Quantidade: " + mamadeiras.getQuantidade() + "ml");

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