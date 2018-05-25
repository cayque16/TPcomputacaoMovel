package br.ufop.cayque.mybabycayque;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.Calendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.models.DadosBebe;


/**
 * A simple {@link Fragment} subclass.
 */
public class DadosBebeFragment extends Fragment {

    private EditText nomeBebe;
    private EditText dataNasci;
    private RadioButton masc, fem;
    private Button btnDadosBebeSalvar;
    private Button btnDadosBebeCancelar;
    private DadosBebe bebeSalvo;
    private Context context;
    private DatePickerDialog.OnDateSetListener dateDialog;
    Calendar cal = Calendar.getInstance();
    private int dia,mes,ano;

    public DadosBebeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dados_bebe, container, false);

        //recupera os componentes da tela
        nomeBebe = view.findViewById(R.id.edtNomeBebe);
        dataNasci = view.findViewById(R.id.dateNasci);
        masc = view.findViewById(R.id.radioButtonMasc);
        fem = view.findViewById(R.id.radioButtonFem);
        btnDadosBebeSalvar = view.findViewById(R.id.btnDadosBebeSalvar);
        btnDadosBebeCancelar = view.findViewById(R.id.btnDadosBebeCancelar);

        //seta os campos caso o bebe ja exista
        if (DadosBebe.getInstance().getBebeNull() == 1) {
            ano = cal.get(Calendar.YEAR);
            mes = cal.get(Calendar.MONTH);
            dia = cal.get(Calendar.DAY_OF_MONTH);
        } else {
            //preenche os campos
            setDados();
        }

        dataNasci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateDialog,
                        ano,mes,dia);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateDialog = new DatePickerDialog.OnDateSetListener(){
            @Override
            public void onDateSet(DatePicker datePicker,int year,int month,int day){
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                dia = day;
                mes = month;
                ano = year;
                dataNasci.setText(date);
            }
        };

        btnDadosBebeSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DadosBebe.getInstance().setNome(nomeBebe.getText().toString());
                if (masc.isChecked()) {
                    DadosBebe.getInstance().setSexo("M");
                } else {
                    DadosBebe.getInstance().setSexo("F");
                }
                DadosBebe.getInstance().setDiaNasc(dia);
                DadosBebe.getInstance().setMesNasc(mes);
                DadosBebe.getInstance().setAnoNasc(ano);
                DadosBebe.saveBebe(getContext());
                Toast.makeText(getActivity(), "Dados Salvos com Sucesso!!!", Toast.LENGTH_SHORT).show();
            }
        });

        btnDadosBebeCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setDados();
            }
        });

        return view;
    }

    private void setDados() {
        nomeBebe.setText(DadosBebe.getInstance().getNome());
        dia = DadosBebe.getInstance().getDiaNasc();
        mes = DadosBebe.getInstance().getMesNasc();
        ano = DadosBebe.getInstance().getAnoNasc();
        dataNasci.setText(Integer.toString(dia)+"/"+Integer.toString(mes)+"/"+Integer.toString(ano));

        if (DadosBebe.getInstance().getSexo().equals("M")) {
            masc.setChecked(true);
        } else {
            fem.setChecked(true);
        }
    }
}
