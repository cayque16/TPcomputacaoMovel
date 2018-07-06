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
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.GeraIdSingleton;
import br.ufop.cayque.mybabycayque.models.Outros;

public class AddOutrosActivity extends AppCompatActivity {

    private EditText data, horaInicio, nota;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private Calendar cal = new GregorianCalendar();
    private DateFormat dateFormat, timeFormat;
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_outros);

        this.setTitle("Nova nota");

        data = findViewById(R.id.dataAddOutroInicio);
        horaInicio = findViewById(R.id.horaAddOutroInicio);
        nota = findViewById(R.id.anotaAddOutro);

        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        ano = cal.get(Calendar.YEAR);

        dateFormat = DateFormat.getDateInstance();
        timeFormat = DateFormat.getTimeInstance();

        data.setText(dateFormat.format(cal.getTime()));
        horaInicio.setText(timeFormat.format(cal.getTime()));

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddOutrosActivity.this,
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

        horaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        AddOutrosActivity.this,
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
                horaInicio.setText(juncao);
            }
        };

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void salvaOutro(View view) {
        int id = GeraIdSingleton.getInstance().geraId(this);
        Outros outros = new Outros("Outro",id,dia,mes,ano,hInicio,mInicio,0,
                dia,mes,ano,hInicio,mInicio,0,nota.getText().toString());

        HistoricoSingleton.getInstance().getOutros().add(outros);
        HistoricoSingleton.getInstance().saveOutros(this);
        outros.addHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
