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

    private EditText dataI, dataT, horaInicio, duracao, anotacao;
    private int diaI, mesI, anoI;
    private int hInicio, mInicio;
    private Calendar cal = new GregorianCalendar();
    private DateFormat dateFormat, timeFormat;
    private DatePickerDialog.OnDateSetListener dateDialogI;
    private DatePickerDialog.OnDateSetListener dateDialogT;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_soneca);

        this.setTitle("Nova soneca");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        diaI = cal.get(Calendar.DAY_OF_MONTH);
        mesI = cal.get(Calendar.MONTH) + 1;
        anoI = cal.get(Calendar.YEAR);

        dataI = findViewById(R.id.dataAddSonecaInicio);
        horaInicio = findViewById(R.id.horaAddSonecaInicio);
        duracao = findViewById(R.id.horaAddSonecaDuracao);
        anotacao = findViewById(R.id.anotaAddSoneca);

        dateFormat = DateFormat.getDateInstance();
        timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);


        dataI.setText(dateFormat.format(cal.getTime()));
        horaInicio.setText(timeFormat.format(cal.getTime()));
        dataT.setText(dateFormat.format(cal.getTime()));
        duracao.setText(timeFormat.format(cal.getTime()));

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

        horaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        AddSonecaActivity.this,
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

                horaInicio.setText(timeFormat.format(cal.getTime()));
            }
        };
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void salvaSoneca(View view) {
        int tempoDuracao;
        tempoDuracao = Integer.parseInt(duracao.getText().toString());
        int id = GeraIdSingleton.getInstance().geraId(this);
        Sonecas sonecas = new Sonecas("Soneca", id, diaI, mesI, anoI, hInicio, mInicio, 0,
                tempoDuracao, 0, anotacao.getText().toString());

        HistoricoSingleton.getInstance().getSonecas().add(sonecas);
        HistoricoSingleton.getInstance().saveSonecas(this);
        sonecas.addHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
