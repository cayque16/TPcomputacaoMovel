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
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.util.Calendar;

import br.ufop.cayque.mybabycayque.R;

public class AddMamadasActivity extends AppCompatActivity {

    private EditText data, horaInicio, horaTermino;
    private RadioGroup radioGroupPeito;
    private int dia, mes, ano;
    private int hInicio,mInicio;
    private int hTermino,mTermino;
    private Calendar cal = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private TimePickerDialog.OnTimeSetListener timeDialogTermino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mamadas);

        this.setTitle("Nova mamada");

        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        ano = cal.get(Calendar.YEAR);

        data = findViewById(R.id.dataMamadaInicio);
        horaInicio = findViewById(R.id.horaMamadaInicio);
        horaTermino = findViewById(R.id.horaMamadaTermino);
        radioGroupPeito = findViewById(R.id.radioGroupPeito);

        data.setText(dia + "/" + mes + "/" + ano);

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddMamadasActivity.this,
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
                        AddMamadasActivity.this,
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

        horaTermino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        AddMamadasActivity.this,
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
                horaTermino.setText(juncao);
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

    public void cancelaMamada(View view) {
    }

    public void salvaMamada(View view) {
    }
}
