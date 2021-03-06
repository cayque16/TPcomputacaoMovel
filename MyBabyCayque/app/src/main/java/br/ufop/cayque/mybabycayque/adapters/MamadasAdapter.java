package br.ufop.cayque.mybabycayque.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.models.Mamadas;

/**
 * Created by cayqu on 25/05/2018.
 */

public class MamadasAdapter extends BaseAdapter {

    private ArrayList<Mamadas> list;
    private Context context;

    public MamadasAdapter(ArrayList<Mamadas> list, Context context) {
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

        Mamadas mamadas = list.get(i);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.mamada_adapter, null);

        TextView tv1 = v.findViewById(R.id.adapMamadaData);
        tv1.setText("Data: " + conversor(mamadas.getDiaInicio()) + "/" + conversor(mamadas.getMesInico()) + "/" + conversor(mamadas.getAnoInicio()));
//        tv1.setText("Data: " + mamadas.getDiaInicio() + "/" + mamadas.getMesInico() + "/" + mamadas.getAnoInicio());

        TextView tv2 = v.findViewById(R.id.adapMamadaHinicio);
        tv2.setText("Hora: " + conversor(mamadas.getHoraInicio()) + ":" + conversor(mamadas.getMinuInicio()));

        TextView tv3 = v.findViewById(R.id.adapMamadaDuracao);
        tv3.setText("Duração: " + mamadas.getDuracao() + " minutos");

        TextView tv4 = v.findViewById(R.id.adapMamadaPeito);
        tv4.setText("Peito: " + mamadas.getPeito());

        TextView tv5 = v.findViewById(R.id.adapMamadaNota);
        tv5.setText("Nota: " + mamadas.getAnotacao());

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
