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

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.GeraIdSingleton;
import br.ufop.cayque.mybabycayque.models.Mamadeiras;

public class AddMamadeirasActivity extends AppCompatActivity {

    private EditText data, horaI, duracao, quanti, anotacao;
    private RadioButton sim, nao;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private Calendar cal = new GregorianCalendar();
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private DateFormat dateFormat, timeFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mamadeiras);

        data = findViewById(R.id.dataAddMamadeiraInicio);
        horaI = findViewById(R.id.horaAddMamadeiraInicio);
        duracao = findViewById(R.id.horaAddMamadeiraDuracao);
        quanti = findViewById(R.id.quantidadeAddMamadeira);
        sim = findViewById(R.id.radioAddButtonSim);
        nao = findViewById(R.id.radioAddButtonNao);
        anotacao = findViewById(R.id.anotaAddMamadeira);

        dateFormat = DateFormat.getDateInstance();
        timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

        this.setTitle("Nova mamadeira");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        ano = cal.get(Calendar.YEAR);

        data.setText(dateFormat.format(cal.getTime()));
        horaI.setText(timeFormat.format(cal.getTime()));
        duracao.setText(timeFormat.format(cal.getTime()));

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
                        cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        timeDialogInicio = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {

                hInicio = i;
                mInicio = i1;

                cal.set(Calendar.HOUR_OF_DAY, i);
                cal.set(Calendar.MINUTE, i1);

                horaI.setText(timeFormat.format(cal.getTime()));
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
        int tempoDuracao;
        tempoDuracao = Integer.parseInt(duracao.getText().toString());
        quantidade = Float.valueOf(quanti.getText().toString());
        if (sim.isChecked()) {
            tudo = 1;
        } else {
            tudo = 0;
        }
        int id = GeraIdSingleton.getInstance().geraId(this);
        Mamadeiras mamadeiras = new Mamadeiras("Mamadeira", id, dia, mes, ano, hInicio, mInicio, 0,
                tempoDuracao, quantidade, tudo, anotacao.getText().toString());

        HistoricoSingleton.getInstance().getMamadeiras().add(mamadeiras);
        HistoricoSingleton.getInstance().saveMamadeiras(this);
        mamadeiras.addHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
