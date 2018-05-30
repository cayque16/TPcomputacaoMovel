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
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Outros;

public class EditOutrosActivity extends AppCompatActivity {

    private EditText data, horaInicio, nota;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private Calendar cal = Calendar.getInstance();
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    int position;
    private ArrayList<Outros> outros = HistoricoSingleton.getInstance().getOutros();
    private AlertDialog alerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_outros);

        this.setTitle("Editar nota");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent it = getIntent();
        position = it.getIntExtra("position", 0);

        data = findViewById(R.id.dataEditOutroInicio);
        horaInicio = findViewById(R.id.horaEditOutroInicio);
        nota = findViewById(R.id.notaEditOutro);

        dia = outros.get(position).getDiaInicio();
        mes = outros.get(position).getMesInico();
        ano = outros.get(position).getAnoInicio();

        data.setText(String.format("%02d", outros.get(position).getDiaInicio()) + "/" +
                String.format("%02d", outros.get(position).getMesInico()) + "/" +
                String.format("%02d", outros.get(position).getAnoInicio()));

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        EditOutrosActivity.this,
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


        horaInicio.setText(String.format("%02d", outros.get(position).getHoraInicio()) + ":" +
                String.format("%02d", outros.get(position).getMinuInicio()) + ":" +
                String.format("%02d", outros.get(position).getSeguInicio()));

        horaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditOutrosActivity.this,
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

        nota.setText(outros.get(position).getNota());
    }

    public void excluiOutro(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção!!!");
        builder.setMessage("Tem certeza que deseja excluir?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                outros.get(position).removeHistorico(EditOutrosActivity.this);
                outros.remove(position);

                HistoricoSingleton.getInstance().saveOutros(EditOutrosActivity.this);
                Toast.makeText(EditOutrosActivity.this, "Operação concluída!!!", Toast.LENGTH_SHORT).show();
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

    public void salvaOutro(View view) {
        int id = outros.get(position).getId();
        Outros outros = new Outros("Outro", id, dia, mes, ano, hInicio, mInicio, 0,
                dia, mes, ano, hInicio, mInicio, 0, nota.getText().toString());

        HistoricoSingleton.getInstance().getOutros().set(position, outros);
        HistoricoSingleton.getInstance().saveOutros(this);
        outros.editHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
