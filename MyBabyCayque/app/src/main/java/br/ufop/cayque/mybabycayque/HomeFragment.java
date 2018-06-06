package br.ufop.cayque.mybabycayque;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import br.ufop.cayque.mybabycayque.adapters.AtividadesAdapter;
import br.ufop.cayque.mybabycayque.adapters.FraldasAdapter;
import br.ufop.cayque.mybabycayque.adapters.MamadasAdapter;
import br.ufop.cayque.mybabycayque.adapters.MamadeirasAdapter;
import br.ufop.cayque.mybabycayque.adapters.MedicamentosAdapter;
import br.ufop.cayque.mybabycayque.adapters.OutrosAdapter;
import br.ufop.cayque.mybabycayque.adapters.SonecasAdapter;
import br.ufop.cayque.mybabycayque.add.AddMedicamentosActivity;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Atividades;
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
    private Button filtro, confirmar, limpar;
    private static final String[] TIPOS = {"Todos", "Mamadas", "Mamadeiras", "Fraldas", "Sonecas", "Medicamentos", "Outros"};
    private String tipoSele;
    private Spinner tipos;
    private int dia, mes, ano;
    private Calendar cal = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener dateDialog;
    private EditText dataFiltro;
    private CheckBox checkData, checkTipo;
    private Boolean checkD = false;
    private Boolean checkT = false;
    private int spinnerIdx = 0;

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
        filtro = view.findViewById(R.id.buttonFiltro);

        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        ano = cal.get(Calendar.YEAR);

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
                        quanti.setText(medicamento.getDose() + " " + medicamento.getUnidadeMedi() + "(s)");
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

        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialog != null) {
                    dialog.setContentView(R.layout.dialog_filtro);
                    tipos = dialog.findViewById(R.id.spinnerTipos);
                    dataFiltro = dialog.findViewById(R.id.filtroData);
                    checkData = dialog.findViewById(R.id.checkData);
                    checkTipo = dialog.findViewById(R.id.checkTipo);
                    confirmar = dialog.findViewById(R.id.buttonConfirmarFiltro);
                    limpar = dialog.findViewById(R.id.buttonLimparFiltro);

                    checkTipo.setChecked(checkT);
                    checkData.setChecked(checkD);

                    capturaData();
                    inicializaSpinner();
                    testaConfirma();
                    testaLimpa();
                    dialog.show();
                }
            }
        });

        return view;
    }

    private void testaLimpa() {
        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkD = checkT = false;
                spinnerIdx = 0;
                dia = cal.get(Calendar.DAY_OF_MONTH);
                mes = cal.get(Calendar.MONTH) + 1;
                ano = cal.get(Calendar.YEAR);
                listView.setAdapter(new AtividadesAdapter(HistoricoSingleton.getInstance().getAtividades(), getContext()));
                dialog.dismiss();
            }
        });
    }

    private void testaConfirma() {
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkTipo.isChecked()) {
                    checkT = true;
                    if (tipoSele.equals(TIPOS[0])) {
                        Collections.sort(HistoricoSingleton.getInstance().getAtividades());
                        listView.setAdapter(new AtividadesAdapter(HistoricoSingleton.getInstance().getAtividades(), getContext()));
                    } else if (tipoSele.equals(TIPOS[1])) {
                        Collections.sort(HistoricoSingleton.getInstance().getMamadas());
                        listView.setAdapter(new MamadasAdapter(HistoricoSingleton.getInstance().getMamadas(), getContext()));
                    } else if (tipoSele.equals(TIPOS[2])) {
                        Collections.sort(HistoricoSingleton.getInstance().getMamadeiras());
                        listView.setAdapter(new MamadeirasAdapter(HistoricoSingleton.getInstance().getMamadeiras(), getContext()));
                    } else if (tipoSele.equals(TIPOS[3])) {
                        Collections.sort(HistoricoSingleton.getInstance().getFraldas());
                        listView.setAdapter(new FraldasAdapter(HistoricoSingleton.getInstance().getFraldas(), getContext()));
                    } else if (tipoSele.equals(TIPOS[4])) {
                        Collections.sort(HistoricoSingleton.getInstance().getSonecas());
                        listView.setAdapter(new SonecasAdapter(HistoricoSingleton.getInstance().getSonecas(), getContext()));
                    } else if (tipoSele.equals(TIPOS[5])) {
                        Collections.sort(HistoricoSingleton.getInstance().getMedicamentos());
                        listView.setAdapter(new MedicamentosAdapter(HistoricoSingleton.getInstance().getMedicamentos(), getContext()));
                    } else if (tipoSele.equals(TIPOS[6])) {
                        Collections.sort(HistoricoSingleton.getInstance().getOutros());
                        listView.setAdapter(new OutrosAdapter(HistoricoSingleton.getInstance().getOutros(), getContext()));
                    }
                    if (checkData.isChecked()) {
                        checkD = true;
                        buscaData(spinnerIdx);
                    }
                } else {
                    checkT = false;
                }

                if ((checkData.isChecked()) && !(checkTipo.isChecked())) {
                    checkD = true;
                    buscaData();
                } else if (!checkTipo.isChecked()) {
                    checkD = false;
                }
                dialog.dismiss();
            }
        });
    }

    private void buscaData() {
        ArrayList<Atividades> listaAux0 = new ArrayList<>();
        listaAux0.clear();
        for (int i = 0; i < HistoricoSingleton.getInstance().getAtividades().size(); i++) {
            if (HistoricoSingleton.getInstance().getAtividades().get(i).comparaData(dia, mes, ano)) {
                listaAux0.add(HistoricoSingleton.getInstance().getAtividades().get(i));
            }
            listView.setAdapter(new AtividadesAdapter(listaAux0, getContext()));
        }
    }

    private void buscaData(int spinnerIdx) {
        ArrayList<Atividades> listaAux0 = new ArrayList<>();
        ArrayList<Mamadas> listaAux1 = new ArrayList<>();
        ArrayList<Mamadeiras> listaAux2 = new ArrayList<>();
        ArrayList<Fraldas> listaAux3 = new ArrayList<>();
        ArrayList<Sonecas> listaAux4 = new ArrayList<>();
        ArrayList<Medicamentos> listaAux5 = new ArrayList<>();
        ArrayList<Outros> listaAux6 = new ArrayList<>();

        switch (spinnerIdx) {
            case 0:
                listaAux0.clear();
                for (int i = 0; i < HistoricoSingleton.getInstance().getAtividades().size(); i++) {
                    if (HistoricoSingleton.getInstance().getAtividades().get(i).comparaData(dia, mes, ano)) {
                        listaAux0.add(HistoricoSingleton.getInstance().getAtividades().get(i));
                    }
                    listView.setAdapter(new AtividadesAdapter(listaAux0, getContext()));
                }
                break;
            case 1:
                listaAux1.clear();
                for (int i = 0; i < HistoricoSingleton.getInstance().getMamadas().size(); i++) {
                    if (HistoricoSingleton.getInstance().getMamadas().get(i).comparaData(dia, mes, ano)) {
                        listaAux1.add(HistoricoSingleton.getInstance().getMamadas().get(i));
                    }
                    listView.setAdapter(new MamadasAdapter(listaAux1, getContext()));
                }
                break;
            case 2:
                listaAux2.clear();
                for (int i = 0; i < HistoricoSingleton.getInstance().getMamadeiras().size(); i++) {
                    if (HistoricoSingleton.getInstance().getMamadeiras().get(i).comparaData(dia, mes, ano)) {
                        listaAux2.add(HistoricoSingleton.getInstance().getMamadeiras().get(i));
                    }
                    listView.setAdapter(new MamadeirasAdapter(listaAux2, getContext()));
                }
                break;
            case 3:
                listaAux3.clear();
                for (int i = 0; i < HistoricoSingleton.getInstance().getFraldas().size(); i++) {
                    if (HistoricoSingleton.getInstance().getFraldas().get(i).comparaData(dia, mes, ano)) {
                        listaAux3.add(HistoricoSingleton.getInstance().getFraldas().get(i));
                    }
                    listView.setAdapter(new FraldasAdapter(listaAux3, getContext()));
                }
                break;
            case 4:
                listaAux4.clear();
                for (int i = 0; i < HistoricoSingleton.getInstance().getSonecas().size(); i++) {
                    if (HistoricoSingleton.getInstance().getSonecas().get(i).comparaData(dia, mes, ano)) {
                        listaAux4.add(HistoricoSingleton.getInstance().getSonecas().get(i));
                    }
                    listView.setAdapter(new SonecasAdapter(listaAux4, getContext()));
                }
                break;
            case 5:
                listaAux5.clear();
                for (int i = 0; i < HistoricoSingleton.getInstance().getMedicamentos().size(); i++) {
                    if (HistoricoSingleton.getInstance().getMedicamentos().get(i).comparaData(dia, mes, ano)) {
                        listaAux5.add(HistoricoSingleton.getInstance().getMedicamentos().get(i));
                    }
                    listView.setAdapter(new MedicamentosAdapter(listaAux5, getContext()));
                }
                break;
            default:
                listaAux6.clear();
                for (int i = 0; i < HistoricoSingleton.getInstance().getOutros().size(); i++) {
                    if (HistoricoSingleton.getInstance().getOutros().get(i).comparaData(dia, mes, ano)) {
                        listaAux6.add(HistoricoSingleton.getInstance().getOutros().get(i));
                    }
                    listView.setAdapter(new OutrosAdapter(listaAux6, getContext()));
                }
                break;
        }
    }

    private void capturaData() {
        dataFiltro.setText(conversor(dia) + "/" + conversor(mes) + "/" + conversor(ano));
        dataFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateDialog,
                        ano, mes, dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        dateDialog = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = conversor(day) + "/" + conversor(month) + "/" + conversor(month);

                dia = day;
                mes = month;
                ano = year;

                dataFiltro.setText(date);
            }
        };
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

    private void inicializaSpinner() {
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, TIPOS);
        tipos.setAdapter(adapterSpinner);
        tipos.setSelection(spinnerIdx);
        tipos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tipoSele = TIPOS[i];
                spinnerIdx = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}