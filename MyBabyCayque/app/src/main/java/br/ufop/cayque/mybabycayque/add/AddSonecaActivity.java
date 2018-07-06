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
import br.ufop.cayque.mybabycayque.models.Sonecas;

public class AddSonecaActivity extends AppCompatActivity {

    private EditText dataI, dataT, horaInicio, horaTermino,anotacao;
    private int diaI, mesI, anoI;
    private int diaT, mesT, anoT;
    private int hInicio,mInicio;
    private int hTermino,mTermino;
    private Calendar cal = new GregorianCalendar();
    private DateFormat dateFormat, timeFormat;
    private DatePickerDialog.OnDateSetListener dateDialogI;
    private DatePickerDialog.OnDateSetListener dateDialogT;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private TimePickerDialog.OnTimeSetListener timeDialogTermino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_soneca);

        this.setTitle("Nova soneca");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        diaI = diaT = cal.get(Calendar.DAY_OF_MONTH);
        mesI = mesT = cal.get(Calendar.MONTH) + 1;
        anoI = anoT = cal.get(Calendar.YEAR);

        dataI = findViewById(R.id.dataAddSonecaInicio);
        dataT = findViewById(R.id.dataAddSonecaTermino);
        horaInicio = findViewById(R.id.horaAddSonecaInicio);
        horaTermino = findViewById(R.id.horaAddSonecaTermino);
        anotacao = findViewById(R.id.anotaAddSoneca);

        dateFormat = DateFormat.getDateInstance();
        timeFormat = DateFormat.getTimeInstance();

        dataI.setText(dateFormat.format(cal.getTime()));
        horaInicio.setText(timeFormat.format(cal.getTime()));
        dataT.setText(dateFormat.format(cal.getTime()));
        horaTermino.setText(timeFormat.format(cal.getTime()));

        dataI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddSonecaActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateDialogI,
                        anoI, mesI, diaI);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateDialogI = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;

                diaI = day;
                mesI = month;
                anoI = year;

                dataI.setText(date);
            }
        };

        dataT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddSonecaActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateDialogT,
                        anoT, mesT, diaT);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateDialogT = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;

                diaT = day;
                mesT = month;
                anoT = year;

                dataT.setText(date);
            }
        };

        horaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        AddSonecaActivity.this,
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
                        AddSonecaActivity.this,
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
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void salvaSoneca(View view) {
        int id = GeraIdSingleton.getInstance().geraId(this);
        Sonecas sonecas = new Sonecas("Soneca",id,diaI,mesI,anoI,hInicio,mInicio,0,
                diaT,mesT,anoT,hTermino,mTermino,0,0,anotacao.getText().toString());

        HistoricoSingleton.getInstance().getSonecas().add(sonecas);
        HistoricoSingleton.getInstance().saveSonecas(this);
        sonecas.addHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
