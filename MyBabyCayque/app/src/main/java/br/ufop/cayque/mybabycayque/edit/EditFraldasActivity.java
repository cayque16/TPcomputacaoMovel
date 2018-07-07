package br.ufop.cayque.mybabycayque.edit;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Fraldas;

public class EditFraldasActivity extends AppCompatActivity {

    private EditText data, horaI, anotacao;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private RadioButton xixi, coco, amb;
    int position;
    private ArrayList<Fraldas> fraldas = HistoricoSingleton.getInstance().getFraldas();
    private AlertDialog alerta;
    private Calendar cal = new GregorianCalendar();
    private DateFormat timeFormat;
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_fraldas);

        this.setTitle("Editar fralda");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent it = getIntent();
        position = it.getIntExtra("position", 0);

        data = findViewById(R.id.dataEditFraldaInicio);
        horaI = findViewById(R.id.horaEditFraldaInicio);
        xixi = findViewById(R.id.radioEditButtonFraldaXixi);
        coco = findViewById(R.id.radioEditButtonFraldaCoco);
        amb = findViewById(R.id.radioEditButtonFraldaAmbos);
        anotacao = findViewById(R.id.anotaEditFralda);

        dia = fraldas.get(position).getDiaInicio();
        mes = fraldas.get(position).getMesInico();
        ano = fraldas.get(position).getAnoInicio();

        cal.set(Calendar.HOUR_OF_DAY, fraldas.get(position).getHoraInicio());
        cal.set(Calendar.MINUTE, fraldas.get(position).getMinuInicio());

        hInicio = cal.get(Calendar.HOUR_OF_DAY);
        mInicio = cal.get(Calendar.MINUTE);

        timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

        data.setText(String.format("%02d", fraldas.get(position).getDiaInicio()) + "/" +
                String.format("%02d", fraldas.get(position).getMesInico()) + "/" +
                String.format("%02d", fraldas.get(position).getAnoInicio()));

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        EditFraldasActivity.this,
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


        horaI.setText(String.format("%02d", fraldas.get(position).getHoraInicio()) + ":" +
                String.format("%02d", fraldas.get(position).getMinuInicio()));


        horaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditFraldasActivity.this,
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

        if (fraldas.get(position).getMotivo().equals("Xixi")) {
            xixi.setChecked(true);
        } else if (fraldas.get(position).getMotivo().equals("Cocô")) {
            coco.setChecked(true);
        } else {
            amb.setChecked(true);
        }

        anotacao.setText(fraldas.get(position).getAnotacao());
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
        int id = fraldas.get(position).getId();
        Fraldas fraldas = new Fraldas("Fralda", id, dia, mes, ano, hInicio, mInicio, 0,
                0, motivo, anotacao.getText().toString());

        HistoricoSingleton.getInstance().getFraldas().set(position, fraldas);
        HistoricoSingleton.getInstance().saveFraldas(this);
        fraldas.editHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void excluiFralda(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção!!!");
        builder.setMessage("Tem certeza que deseja excluir?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                fraldas.get(position).removeHistorico(EditFraldasActivity.this);
                fraldas.remove(position);

                HistoricoSingleton.getInstance().saveFraldas(EditFraldasActivity.this);

                Toast.makeText(EditFraldasActivity.this, "Operação concluída!!!", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alerta = builder.create();
        alerta.show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
