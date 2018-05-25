package br.ufop.cayque.mybabycayque;


import android.content.Context;
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

import br.ufop.cayque.mybabycayque.modelo.DadosBebe;


/**
 * A simple {@link Fragment} subclass.
 */
public class DadosBebeFragment extends Fragment {

    private EditText nomeBebe;
    private DatePicker dataNasci;
    private RadioButton masc, fem;
    private Button btnDadosBebeSalvar;
    private Button btnDadosBebeCancelar;
    private DadosBebe bebeSalvo;
    private Context context;

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
            //os campos ja vem zerados
        } else {
            //preenche os campos
            setDados();
        }

        btnDadosBebeSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DadosBebe.getInstance().setNome(nomeBebe.getText().toString());
                if (masc.isChecked()) {
                    DadosBebe.getInstance().setSexo("M");
                } else {
                    DadosBebe.getInstance().setSexo("F");
                }
                DadosBebe.getInstance().setDiaNasc(dataNasci.getDayOfMonth());
                DadosBebe.getInstance().setMesNasc(dataNasci.getMonth());
                DadosBebe.getInstance().setAnoNasc(dataNasci.getYear());
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
        dataNasci.updateDate(DadosBebe.getInstance().getAnoNasc(),
                DadosBebe.getInstance().getMesNasc(),
                DadosBebe.getInstance().getDiaNasc());

        if (DadosBebe.getInstance().getSexo().equals("M")) {
            masc.setChecked(true);
        } else {
            fem.setChecked(true);
        }
    }
}
