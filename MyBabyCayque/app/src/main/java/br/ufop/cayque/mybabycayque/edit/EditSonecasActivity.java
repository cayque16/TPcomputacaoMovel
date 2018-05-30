package br.ufop.cayque.mybabycayque.edit;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.add.AddSonecaActivity;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Sonecas;

public class EditSonecasActivity extends AppCompatActivity {

    private EditText dataI, dataT, horaInicio, horaTermino;
    private int diaI, mesI, anoI;
    private int diaT, mesT, anoT;
    private int hInicio, mInicio;
    private int hTermino, mTermino;
    int position;
    private ArrayList<Sonecas> sonecas = HistoricoSingleton.getInstance().getSonecas();
    private AlertDialog alerta;
    private DatePickerDialog.OnDateSetListener dateDialogI;
    private DatePickerDialog.OnDateSetListener dateDialogT;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private TimePickerDialog.OnTimeSetListener timeDialogTermino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_sonecas);

        this.setTitle("Editar soneca");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent it = getIntent();
        position = it.getIntExtra("position", 0);

        dataI = findViewById(R.id.dataEditSonecaInicio);
        dataT = findViewById(R.id.dataEditSonecaTermino);
        horaInicio = findViewById(R.id.horaEditSonecaInicio);
        horaTermino = findViewById(R.id.horaEditSonecaTermino);

        diaI = diaT = sonecas.get(position).getDiaInicio();
        mesI = mesT = sonecas.get(position).getMesInico();
        anoI = anoT = sonecas.get(position).getAnoInicio();

        dataI.setText(String.format("%02d", sonecas.get(position).getDiaInicio()) + "/" +
                String.format("%02d", sonecas.get(position).getMesInico()) + "/" +
                String.format("%02d", sonecas.get(position).getAnoInicio()));

        dataT.setText(String.format("%02d", sonecas.get(position).getDiaTermino()) + "/" +
                String.format("%02d", sonecas.get(position).getMesTermino()) + "/" +
                String.format("%02d", sonecas.get(position).getAnoTermino()));

        dataI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        EditSonecasActivity.this,
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
                        EditSonecasActivity.this,
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
        Log.v("Erro Aqui","3");
        horaInicio.setText(String.format("%02d", sonecas.get(position).getHoraInicio()) + ":" +
                String.format("%02d", sonecas.get(position).getMinuInicio()) + ":" +
                String.format("%02d", sonecas.get(position).getSeguInicio()));

        horaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditSonecasActivity.this,
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
                        EditSonecasActivity.this,
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
        Log.v("Erro Aqui","4");
        horaTermino.setText(String.format("%02d", sonecas.get(position).getHoraTermino()) + ":" +
                String.format("%02d", sonecas.get(position).getMinuTermino()) + ":" +
                String.format("%02d", sonecas.get(position).getSeguTermino()));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void salvaSoneca(View view) {
        int id = sonecas.get(position).getId();
        Sonecas sonecas = new Sonecas("Soneca", id,diaI, mesI, anoI, hInicio, mInicio, 0,
                diaI, mesI, anoI, hTermino, mTermino, 0, 0);

        HistoricoSingleton.getInstance().getSonecas().set(position, sonecas);
        HistoricoSingleton.getInstance().saveSonecas(this);
        sonecas.editHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();

    }

    public void excluiSoneca(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção!!!");
        builder.setMessage("Tem certeza que deseja excluir?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                sonecas.get(position).removeHistorico(EditSonecasActivity.this);
                sonecas.remove(position);

                HistoricoSingleton.getInstance().saveSonecas(EditSonecasActivity.this);
                Toast.makeText(EditSonecasActivity.this, "Operação concluída!!!", Toast.LENGTH_SHORT).show();
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


}
