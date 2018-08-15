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
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import br.ufop.cayque.mybabycayque.adapters.AtividadesAdapter;
import br.ufop.cayque.mybabycayque.adapters.FraldasAdapter;
import br.ufop.cayque.mybabycayque.adapters.MamadasAdapter;
import br.ufop.cayque.mybabycayque.adapters.MamadeirasAdapter;
import br.ufop.cayque.mybabycayque.adapters.MedicamentosAdapter;
import br.ufop.cayque.mybabycayque.adapters.OutrosAdapter;
import br.ufop.cayque.mybabycayque.adapters.SonecasAdapter;
import br.ufop.cayque.mybabycayque.add.AddFraldaActivity;
import br.ufop.cayque.mybabycayque.add.AddMamadasActivity;
import br.ufop.cayque.mybabycayque.add.AddMamadeirasActivity;
import br.ufop.cayque.mybabycayque.add.AddMedicamentosActivity;
import br.ufop.cayque.mybabycayque.add.AddOutrosActivity;
import br.ufop.cayque.mybabycayque.add.AddSonecaActivity;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.edit.EditFraldasActivity;
import br.ufop.cayque.mybabycayque.edit.EditMamadasActivity;
import br.ufop.cayque.mybabycayque.edit.EditMamadeirasActivity;
import br.ufop.cayque.mybabycayque.edit.EditMedicamentosActivity;
import br.ufop.cayque.mybabycayque.edit.EditOutrosActivity;
import br.ufop.cayque.mybabycayque.edit.EditSonecasActivity;
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
    private TextView textVazia;
    private FloatingActionButton fabMamada, fabMamadeira, fabFralda, fabSoneca;
    private FloatingActionButton fabMedicamento, fabOutro;

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
        textVazia = view.findViewById(R.id.textHomeVazia);
        fabMamada = view.findViewById(R.id.menu_item_mamada);
        fabMamadeira = view.findViewById(R.id.menu_item_mamadeira);
        fabFralda = view.findViewById(R.id.menu_item_fralda);
        fabSoneca = view.findViewById(R.id.menu_item_soneca);
        fabMedicamento = view.findViewById(R.id.menu_item_medicamento);
        fabOutro = view.findViewById(R.id.menu_item_outro);

        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        ano = cal.get(Calendar.YEAR);

        Collections.sort(HistoricoSingleton.getInstance().getAtividades());

        mensagemListaVazia();

        listView.setAdapter(new AtividadesAdapter(HistoricoSingleton.getInstance().getAtividades(), getContext()));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                String tipo = HistoricoSingleton.getInstance().getAtividades().get(i).getTipo();
//                int id = HistoricoSingleton.getInstance().getAtividades().get(i).getId();
                Atividades atividade = (Atividades) adapterView.getItemAtPosition(i);
                String tipo = atividade.getTipo();
                int id = atividade.getId();
                int sizeArray;

                if (tipo.equals("Mamada")) {
                    sizeArray = HistoricoSingleton.getInstance().getMamadas().size();
                    Intent it = new Intent(getContext(), EditMamadasActivity.class);
                    for (int j = 0; j < sizeArray; j++) {
                        if (HistoricoSingleton.getInstance().getMamadas().get(j).getId() == id) {
                            it.putExtra("position", j);
                            break;
                        }
                    }
                    startActivity(it);
                } else if (tipo.equals("Mamadeira")) {
                    sizeArray = HistoricoSingleton.getInstance().getMamadeiras().size();
                    Intent it = new Intent(getContext(), EditMamadeirasActivity.class);
                    for (int j = 0; j < sizeArray; j++) {
                        if (HistoricoSingleton.getInstance().getMamadeiras().get(j).getId() == id) {
                            it.putExtra("position", j);
                            break;
                        }
                    }
                    startActivity(it);
                } else if (tipo.equals("Fralda")) {
                    sizeArray = HistoricoSingleton.getInstance().getFraldas().size();
                    Intent it = new Intent(getContext(), EditFraldasActivity.class);
                    for (int j = 0; j < sizeArray; j++) {
                        if (HistoricoSingleton.getInstance().getFraldas().get(j).getId() == id) {
                            it.putExtra("position", j);
                            break;
                        }
                    }
                    startActivity(it);
                } else if (tipo.equals("Soneca")) {
                    sizeArray = HistoricoSingleton.getInstance().getSonecas().size();
                    Intent it = new Intent(getContext(), EditSonecasActivity.class);
                    for (int j = 0; j < sizeArray; j++) {
                        if (HistoricoSingleton.getInstance().getSonecas().get(j).getId() == id) {
                            it.putExtra("position", j);
                            break;
                        }
                    }
                    startActivity(it);
                } else if (tipo.equals("Medicamento")) {
                    sizeArray = HistoricoSingleton.getInstance().getMedicamentos().size();
                    Intent it = new Intent(getContext(), EditMedicamentosActivity.class);
                    for (int j = 0; j < sizeArray; j++) {
                        if (HistoricoSingleton.getInstance().getMedicamentos().get(j).getId() == id) {
                            it.putExtra("position", j);
                            break;
                        }
                    }
                    startActivity(it);
                } else if (tipo.equals("Outro")) {
                    sizeArray = HistoricoSingleton.getInstance().getOutros().size();
                    Intent it = new Intent(getContext(), EditOutrosActivity.class);
                    for (int j = 0; j < sizeArray; j++) {
                        if (HistoricoSingleton.getInstance().getOutros().get(j).getId() == id) {
                            it.putExtra("position", j);
                            break;
                        }
                    }
                    startActivity(it);
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

        fabMamada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), AddMamadasActivity.class);
                startActivity(it);
            }
        });

        fabMamadeira.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), AddMamadeirasActivity.class);
                startActivity(it);
            }
        });

        fabFralda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), AddFraldaActivity.class);
                startActivity(it);
            }
        });

        fabSoneca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), AddSonecaActivity.class);
                startActivity(it);
            }
        });

        fabMedicamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), AddMedicamentosActivity.class);
                startActivity(it);
            }
        });

        fabOutro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(getContext(), AddOutrosActivity.class);
                startActivity(it);
            }
        });

        return view;
    }

    private void mensagemListaVazia() {
        if (HistoricoSingleton.getInstance().getAtividades().isEmpty()) {
            textVazia.setVisibility(View.VISIBLE);
        } else {
            textVazia.setVisibility(View.INVISIBLE);
        }
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
                String date = conversor(day) + "/" + conversor(month) + "/" + conversor(year);

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
//        listView.setAdapter(new AtividadesAdapter(HistoricoSingleton.getInstance().getAtividades(), getContext()));
        mensagemListaVazia();
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