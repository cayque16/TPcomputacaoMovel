package br.ufop.cayque.mybabycayque.edit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Mamadas;

public class EditMamadasActivity extends AppCompatActivity {

    private EditText data, horaI, horaT;
    private RadioButton dir, esq, amb;
    private ArrayList<Mamadas> mamadas = HistoricoSingleton.getInstance().getMamadas();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mamadas);

        this.setTitle("Editar mamada");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent it = getIntent();
        int position = it.getIntExtra("position", 0);

        data = findViewById(R.id.dataEditMamadaInicio);
        data.setText(conversor(HistoricoSingleton.getInstance().getMamadas().get(position).getDiaInicio()) + "/" +
                conversor(HistoricoSingleton.getInstance().getMamadas().get(position).getMesInico()) + "/" +
                conversor(HistoricoSingleton.getInstance().getMamadas().get(position).getAnoInicio()));
        horaI = findViewById(R.id.horaEditaMamadaInicio);
        horaI.setText(conversor(HistoricoSingleton.getInstance().getMamadas().get(position).getHoraInicio()) + ":" +
                conversor(HistoricoSingleton.getInstance().getMamadas().get(position).getMinuInicio()) + ":" +
                conversor(HistoricoSingleton.getInstance().getMamadas().get(position).getSeguInicio()));
        horaT = findViewById(R.id.horaEditMamadaTermino);
        horaT.setText(conversor(HistoricoSingleton.getInstance().getMamadas().get(position).getHoraTermino()) + ":" +
                conversor(HistoricoSingleton.getInstance().getMamadas().get(position).getMinuTermino()) + ":" +
                conversor(HistoricoSingleton.getInstance().getMamadas().get(position).getSeguTermino()));
        dir = findViewById(R.id.radioEditButtonDirei);
        esq = findViewById(R.id.radioEditButtonEsque);
        amb = findViewById(R.id.radioEditButtonAmbos);
        if (mamadas.get(position).getPeito().equals("Direito")) {
            dir.setChecked(true);
        } else if (mamadas.get(position).getPeito().equals("Esquerdo")) {
            esq.setChecked(true);
        } else {
            amb.setChecked(true);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public String conversor(int valor) {
        String temp = "0";
        if (valor < 10) {
            return temp + valor;
        }
        return Integer.toString(valor);
    }
}
