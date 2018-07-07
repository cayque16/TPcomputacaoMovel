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
import java.util.Date;
import java.util.GregorianCalendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Fraldas;
import br.ufop.cayque.mybabycayque.models.GeraIdSingleton;

public class AddFraldaActivity extends AppCompatActivity {

    private EditText data, horaInicio, anotacao;
    private RadioButton xixi, coco, ambos;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private Calendar cal = new GregorianCalendar();
    private DateFormat dateFormat, timeFormat;
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fralda);

        this.setTitle("Nova fralda");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        data = findViewById(R.id.dataAddFraldaInicio);
        horaInicio = findViewById(R.id.horaAddFraldaInicio);
        xixi = findViewById(R.id.radioAddButtonFraldaXixi);
        coco = findViewById(R.id.radioAddButtonFraldaCoco);
        ambos = findViewById(R.id.radioAddButtonFraldaAmbos);
        anotacao = findViewById(R.id.anotaAddFralda);

        dateFormat = DateFormat.getDateInstance();
        timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        ano = cal.get(Calendar.YEAR);

        hInicio = cal.get(Calendar.HOUR_OF_DAY);
        mInicio = cal.get(Calendar.MINUTE);

        data.setText(dateFormat.format(cal.getTime()));
        horaInicio.setText(timeFormat.format(cal.getTime()));

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddFraldaActivity.this,
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
                        AddFraldaActivity.this,
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

    public void salvaFralda(View view) {
        String motivo;
        if (xixi.isChecked()) {
            motivo = "Xixi";
        } else if (coco.isChecked()) {
            motivo = "Cocô";
        } else {
            motivo = "Ambos";
        }
        int id = GeraIdSingleton.getInstance().geraId(this);
        Fraldas fraldas = new Fraldas("Fralda", id, dia, mes, ano, hInicio, mInicio, 0,
                0, motivo, anotacao.getText().toString());

        HistoricoSingleton.getInstance().getFraldas().add(fraldas);
        HistoricoSingleton.getInstance().saveFraldas(this);
        fraldas.addHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
