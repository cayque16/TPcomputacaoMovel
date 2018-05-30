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

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Fraldas;

public class EditFraldasActivity extends AppCompatActivity {

    private EditText data, horaI;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private RadioButton xixi, coco, amb;
    int position;
    private ArrayList<Fraldas> fraldas = HistoricoSingleton.getInstance().getFraldas();
    private AlertDialog alerta;
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private TimePickerDialog.OnTimeSetListener timeDialogTermino;

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

        dia = fraldas.get(position).getDiaInicio();
        mes = fraldas.get(position).getMesInico();
        ano = fraldas.get(position).getAnoInicio();

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
                String.format("%02d", fraldas.get(position).getMinuInicio()) + ":" +
                String.format("%02d", fraldas.get(position).getSeguInicio()));

        horaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditFraldasActivity.this,
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

        if (fraldas.get(position).getMotivo().equals("Xixi")) {
            xixi.setChecked(true);
        } else if (fraldas.get(position).getMotivo().equals("Cocô")) {
            coco.setChecked(true);
        } else {
            amb.setChecked(true);
        }
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
                dia, mes, ano, hInicio, mInicio, 0, motivo);

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
