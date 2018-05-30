package br.ufop.cayque.mybabycayque.add;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.GeraIdSingleton;
import br.ufop.cayque.mybabycayque.models.Mamadeiras;

public class AddMamadeirasActivity extends AppCompatActivity {

    private EditText data, horaI, horaT, quanti;
    private RadioButton sim, nao;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private int hTermino, mTermino;
    private Calendar cal = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private TimePickerDialog.OnTimeSetListener timeDialogTermino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mamadeiras);

        data = findViewById(R.id.dataAddMamadeiraInicio);
        horaI = findViewById(R.id.horaAddMamadeiraInicio);
        horaT = findViewById(R.id.horaAddMamadeiraTermino);
        quanti = findViewById(R.id.quantidadeAddMamadeira);
        sim = findViewById(R.id.radioAddButtonSim);
        nao = findViewById(R.id.radioAddButtonNao);

        this.setTitle("Nova mamadeira");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        ano = cal.get(Calendar.YEAR);

        data.setText(dia + "/" + mes + "/" + ano);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddMamadeirasActivity.this,
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
                String date = day + "/" + month + "/" + year;

                dia = day;
                mes = month;
                ano = year;

                data.setText(date);
            }
        };

        horaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        AddMamadeirasActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        timeDialogInicio,
                        0, 0, true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        timeDialogInicio = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String horas;
                String minutos;

                if (i < 10) {
                    horas = "0" + i + ":";
                } else {
                    horas = i + ":";
                }

                if (i1 < 10) {
                    minutos = "0" + i1 + ":00";
                } else {
                    minutos = i1 + ":00";

                }

                hInicio = i;
                mInicio = i1;

                String juncao = horas + minutos;
                horaI.setText(juncao);
            }
        };

        horaT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        AddMamadeirasActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        timeDialogTermino,
                        0, 0, true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        timeDialogTermino = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                String horas;
                String minutos;

                if (i < 10) {
                    horas = "0" + i + ":";
                } else {
                    horas = i + ":";
                }

                if (i1 < 10) {
                    minutos = "0" + i1 + ":00";
                } else {
                    minutos = i1 + ":00";

                }

                hTermino = i;
                mTermino = i1;

                String juncao = horas + minutos;
                horaT.setText(juncao);
            }
        };

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void salvaMamadeira(View view) {
        int tudo;
        float quantidade;
        quantidade = Float.valueOf(quanti.getText().toString());
        if(sim.isChecked()){
            tudo = 1;
        } else{
            tudo = 0;
        }
        int id = GeraIdSingleton.getInstance().geraId(this);
        Mamadeiras mamadeiras = new Mamadeiras("Mamadeira",id,dia,mes,ano,hInicio,mInicio,0,
                dia,mes,ano,hTermino,mTermino,0,quantidade,tudo);

        HistoricoSingleton.getInstance().getMamadeiras().add(mamadeiras);
        HistoricoSingleton.getInstance().saveMamadeiras(this);
        mamadeiras.addHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
