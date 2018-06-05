package br.ufop.cayque.mybabycayque;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Collections;
import java.util.Date;

import br.ufop.cayque.mybabycayque.adapters.AtividadesAdapter;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Fraldas;
import br.ufop.cayque.mybabycayque.models.GeraIdSingleton;
import br.ufop.cayque.mybabycayque.models.Mamadas;
import br.ufop.cayque.mybabycayque.models.Mamadeiras;
import br.ufop.cayque.mybabycayque.models.Medicamentos;
import br.ufop.cayque.mybabycayque.models.Outros;
import br.ufop.cayque.mybabycayque.models.Sonecas;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private ListView listView;
    private Dialog dialog;
    private Date data1, data2;


    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        GeraIdSingleton.loadGeraId(getContext());

        HistoricoSingleton.getInstance().loadAtividades(getContext());

        dialog = new Dialog(getContext());

        listView = view.findViewById(R.id.listaAtividades);

        Collections.sort(HistoricoSingleton.getInstance().getAtividades());

        listView.setAdapter(new AtividadesAdapter(HistoricoSingleton.getInstance().getAtividades(), getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String tipo = HistoricoSingleton.getInstance().getAtividades().get(i).getTipo();
                TextView dataI, dataT, horaI, horaT, peito, quanti;
                TextView anotacao, tudo, motivo, nome;

                if (tipo.equals("Mamada")) {
                    Mamadas mamada = (Mamadas) HistoricoSingleton.getInstance().getAtividades().get(i);
                    if (dialog != null) {
                        dialog.setContentView(R.layout.dialog_mamada);
                        dataI = dialog.findViewById(R.id.dialogMamadaDataI);
                        dataI.setText(conversor(mamada.getDiaInicio()) + "/" + conversor(mamada.getMesInico()) + "/" + conversor(mamada.getAnoInicio()));
                        horaI = dialog.findViewById(R.id.dialogMamadaHoraI);
                        horaI.setText(conversor(mamada.getHoraInicio()) + ":" + conversor(mamada.getMinuInicio()) + ":" + conversor(mamada.getSeguInicio()));
                        horaT = dialog.findViewById(R.id.dialogMamadaHoraT);
                        horaT.setText(conversor(mamada.getHoraTermino()) + ":" + conversor(mamada.getMinuTermino()) + ":" + conversor(mamada.getSeguTermino()));
                        peito = dialog.findViewById(R.id.dialogMamadaPeito);
                        peito.setText(mamada.getPeito());
                        anotacao = dialog.findViewById(R.id.dialogMamadaAnotacao);
                        anotacao.setText(mamada.getAnotacao());
                        dialog.show();
                    }
                } else if (tipo.equals("Mamadeira")) {
                    Mamadeiras mamadeira = (Mamadeiras) HistoricoSingleton.getInstance().getAtividades().get(i);
                    if (dialog != null) {
                        dialog.setContentView(R.layout.dialog_mamadeira);
                        dataI = dialog.findViewById(R.id.dialogMamadeiraDataI);
                        dataI.setText(conversor(mamadeira.getDiaInicio()) + "/" + conversor(mamadeira.getMesInico()) + "/" + conversor(mamadeira.getAnoInicio()));
                        horaI = dialog.findViewById(R.id.dialogMamadeiraHoraI);
                        horaI.setText(conversor(mamadeira.getHoraInicio()) + ":" + conversor(mamadeira.getMinuInicio()) + ":" + conversor(mamadeira.getSeguInicio()));
                        horaT = dialog.findViewById(R.id.dialogMamadeiraHoraT);
                        horaT.setText(conversor(mamadeira.getHoraTermino()) + ":" + conversor(mamadeira.getMinuTermino()) + ":" + conversor(mamadeira.getSeguTermino()));
                        quanti = dialog.findViewById(R.id.dialogMamadeiraQuanti);
                        quanti.setText(mamadeira.getQuantidade() + "ml");
                        tudo = dialog.findViewById(R.id.dialogMamadeiraTomou);
                        if (mamadeira.getTomouTudo() == 1) {
                            tudo.setText("Sim");
                        } else {
                            tudo.setText("Não");
                        }
                        anotacao = dialog.findViewById(R.id.dialogMamadeiraAnotacao);
                        anotacao.setText(mamadeira.getAnotacao());
                        dialog.show();
                    }
                } else if (tipo.equals("Fralda")) {
                    Fraldas fralda = (Fraldas) HistoricoSingleton.getInstance().getAtividades().get(i);
                    if (dialog != null) {
                        dialog.setContentView(R.layout.dialog_fralda);
                        dataI = dialog.findViewById(R.id.dialogFraldaDataI);
                        dataI.setText(conversor(fralda.getDiaInicio()) + "/" + conversor(fralda.getMesInico()) + "/" + conversor(fralda.getAnoInicio()));
                        horaI = dialog.findViewById(R.id.dialogFraldaHoraI);
                        horaI.setText(conversor(fralda.getHoraInicio()) + ":" + conversor(fralda.getMinuInicio()) + ":" + conversor(fralda.getSeguInicio()));
                        motivo = dialog.findViewById(R.id.dialogFraldaMotivo);
                        motivo.setText(fralda.getMotivo());
                        anotacao = dialog.findViewById(R.id.dialogFraldaAnotacao);
                        anotacao.setText(fralda.getAnotacao());
                        dialog.show();
                    }
                } else if (tipo.equals("Soneca")) {
                    Sonecas soneca = (Sonecas) HistoricoSingleton.getInstance().getAtividades().get(i);
                    if (dialog != null) {
                        dialog.setContentView(R.layout.dialog_soneca);
                        dataI = dialog.findViewById(R.id.dialogSonecaDataI);
                        dataI.setText(conversor(soneca.getDiaInicio()) + "/" + conversor(soneca.getMesInico()) + "/" + conversor(soneca.getAnoInicio()));
                        dataT = dialog.findViewById(R.id.dialogSonecaDataT);
                        dataT.setText(conversor(soneca.getDiaTermino()) + "/" + conversor(soneca.getMesTermino()) + "/" + conversor(soneca.getAnoTermino()));
                        horaI = dialog.findViewById(R.id.dialogSonecaHoraI);
                        horaI.setText(conversor(soneca.getHoraInicio()) + ":" + conversor(soneca.getMinuInicio()) + ":" + conversor(soneca.getSeguInicio()));
                        horaT = dialog.findViewById(R.id.dialogSonecaHoraT);
                        horaT.setText(conversor(soneca.getHoraTermino()) + ":" + conversor(soneca.getMinuTermino()) + ":" + conversor(soneca.getSeguTermino()));
                        anotacao = dialog.findViewById(R.id.dialogSonecaAnotacao);
                        anotacao.setText(soneca.getAnotacao());
                        dialog.show();
                    }
                } else if (tipo.equals("Medicamento")) {
                    Medicamentos medicamento = (Medicamentos) HistoricoSingleton.getInstance().getAtividades().get(i);
                    if (dialog != null) {
                        dialog.setContentView(R.layout.dialog_medicamento);
                        dataI = dialog.findViewById(R.id.dialogMedicamentoDataI);
                        dataI.setText(conversor(medicamento.getDiaInicio()) + "/" + conversor(medicamento.getMesInico()) + "/" + conversor(medicamento.getAnoInicio()));
                        horaI = dialog.findViewById(R.id.dialogMedicamentoHoraI);
                        horaI.setText(conversor(medicamento.getHoraInicio()) + ":" + conversor(medicamento.getMinuInicio()) + ":" + conversor(medicamento.getSeguInicio()));
                        nome = dialog.findViewById(R.id.dialogMedicamentoNome);
                        nome.setText(medicamento.getNome());
                        quanti = dialog.findViewById(R.id.dialogMedicamentoQuanti);
                        quanti.setText(medicamento.getDose()+" "+medicamento.getUnidadeMedi()+"(s)");
                        anotacao = dialog.findViewById(R.id.dialogMedicamentoAnotacao);
                        anotacao.setText(medicamento.getAnotacao());
                        dialog.show();
                    }
                } else if (tipo.equals("Outro")) {
                    Outros outro = (Outros) HistoricoSingleton.getInstance().getAtividades().get(i);
                    if (dialog != null) {
                        dialog.setContentView(R.layout.dialog_outro);
                        dataI = dialog.findViewById(R.id.dialogOutroDataI);
                        dataI.setText(conversor(outro.getDiaInicio()) + "/" + conversor(outro.getMesInico()) + "/" + conversor(outro.getAnoInicio()));
                        horaI = dialog.findViewById(R.id.dialogOutroHoraI);
                        horaI.setText(conversor(outro.getHoraInicio()) + ":" + conversor(outro.getMinuInicio()) + ":" + conversor(outro.getSeguInicio()));
                        anotacao = dialog.findViewById(R.id.dialogOutroAnotacao);
                        anotacao.setText(outro.getAnotacao());
                        dialog.show();
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        //para atualizar o ListView quando voltar da tela de Add
        listView.setAdapter(new AtividadesAdapter(HistoricoSingleton.getInstance().getAtividades(), getContext()));
    }

    public String conversor(int valor) {
        String temp = "0";
        if (valor < 10) {
            return temp + valor;
        }
        return Integer.toString(valor);
    }
}