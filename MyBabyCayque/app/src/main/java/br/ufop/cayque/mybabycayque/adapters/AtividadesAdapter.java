package br.ufop.cayque.mybabycayque.adapters;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.models.Atividades;

/**
 * Created by cayqu on 30/05/2018.
 */

public class AtividadesAdapter extends BaseAdapter {
    private ArrayList<Atividades> list;
    private Context context;

    public AtividadesAdapter(ArrayList<Atividades> list, Context context) {
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

        Atividades atividades = list.get(i);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.atividade_adapter, null);

        ImageView icone = v.findViewById(R.id.adapAtividadeImg);
        if (atividades.getTipo().equals("Mamada")) {
            icone.setBackgroundResource(R.drawable.img_background_mamada);
            icone.setImageResource(R.drawable.img_mamadas);
        } else if (atividades.getTipo().equals("Mamadeira")) {
            icone.setBackgroundResource(R.drawable.img_background_mamadeira);
            icone.setImageResource(R.drawable.img_mamadeira);
        } else if (atividades.getTipo().equals("Fralda")) {
            icone.setBackgroundResource(R.drawable.img_background_fralda);
            icone.setImageResource(R.drawable.img_fralda);
        } else if (atividades.getTipo().equals("Soneca")) {
            icone.setBackgroundResource(R.drawable.img_background_soneca);
            icone.setImageResource(R.drawable.img_soneca);
        } else if (atividades.getTipo().equals("Medicamento")) {
            icone.setBackgroundResource(R.drawable.img_background_medicamento);
            icone.setImageResource(R.drawable.img_medicamento);
        } else if (atividades.getTipo().equals("Outro")) {
            icone.setBackgroundResource(R.drawable.img_background_outros);
            icone.setImageResource(R.drawable.img_outros);
        }

        TextView tv1 = v.findViewById(R.id.adapAtividadeTipo);
        tv1.setText(atividades.getTipo());

        TextView tv2 = v.findViewById(R.id.adapAtividadeData);
        tv2.setText("Data: " + conversor(atividades.getDiaInicio()) + "/" + conversor(atividades.getMesInico()) + "/" + conversor(atividades.getAnoInicio()));

        TextView tv3 = v.findViewById(R.id.adapAtividadeHinicio);
        tv3.setText("Hora: " + conversor(atividades.getHoraInicio()) + ":" + conversor(atividades.getMinuInicio()) + ":" + conversor(atividades.getSeguInicio()));

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
