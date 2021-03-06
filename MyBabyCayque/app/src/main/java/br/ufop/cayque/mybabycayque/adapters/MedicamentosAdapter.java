package br.ufop.cayque.mybabycayque.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.models.Medicamentos;

/**
 * Created by cayqu on 29/05/2018.
 */

public class MedicamentosAdapter extends BaseAdapter {
    private ArrayList<Medicamentos> list;
    private Context context;

    public MedicamentosAdapter(ArrayList<Medicamentos> list, Context context) {
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

        Medicamentos medicamentos = list.get(i);

        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService
                        (Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.adapter_medicamento, null);

        TextView tv1 = v.findViewById(R.id.adapMedicamentoData);
        tv1.setText("Data: " + conversor(medicamentos.getDiaInicio()) + "/" + conversor(medicamentos.getMesInico()) + "/" + conversor(medicamentos.getAnoInicio()));

        TextView tv2 = v.findViewById(R.id.adapMedicamentoHinicio);
        tv2.setText("Hora: " + conversor(medicamentos.getHoraInicio()) + ":" + conversor(medicamentos.getMinuInicio()));

        TextView tv3 = v.findViewById(R.id.adapMedicamentoDose);
        tv3.setText(medicamentos.getDose() + " " + medicamentos.getUnidadeMedi() + " (s)");

        TextView tv4 = v.findViewById(R.id.adapMedicamentoNome);
        tv4.setText(medicamentos.getNome());

        TextView tv5 = v.findViewById(R.id.adapMedicamentoNota);
        tv5.setText("Nota: " + medicamentos.getAnotacao());

        TextView tv6 = v.findViewById(R.id.adapMedicamentoNotifica);
        if(medicamentos.getNotificacao() == 0){
            tv6.setText("Dose única");
        } else {
            switch (medicamentos.getFrequenciaNotifica()) {
                case Medicamentos.TODO_DIA:
                    tv6.setText("Todo dia");
                    break;
                case Medicamentos.DOZE_EM_DOZE:
                    tv6.setText("De 12 em 12 horas");
                    break;
                case Medicamentos.OITO_EM_OITO:
                    tv6.setText("De 8 em 8 horas");
                    break;
                case Medicamentos.SEIS_EM_SEIS:
                    tv6.setText("De 6 em 6 horas");
                    break;
                case Medicamentos.QUATRO_EM_QUATRO:
                    tv6.setText("De 4 em 4 horas");
                    break;
            }
        }

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
