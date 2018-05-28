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
import br.ufop.cayque.mybabycayque.models.Mamadas;
import br.ufop.cayque.mybabycayque.models.Mamadeiras;

public class EditMamadeirasActivity extends AppCompatActivity {

    private EditText data, horaI, horaT, quantidade;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private int hTermino, mTermino;
    private RadioButton sim, nao;
    int position;
    private ArrayList<Mamadeiras> mamadeiras = HistoricoSingleton.getInstance().getMamadeiras();
    private AlertDialog alerta;
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private TimePickerDialog.OnTimeSetListener timeDialogTermino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mamadeiras);

        setTitle("Editar mamadeira");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent it = getIntent();
        position = it.getIntExtra("position", 0);

        data = findViewById(R.id.dataEditMamadeiraInicio);
        horaI = findViewById(R.id.horaEditMamadeiraInicio);
        horaT = findViewById(R.id.horaEditMamadeiraTermino);
        sim = findViewById(R.id.radioEditButtonSim);
        nao = findViewById(R.id.radioEditButtonNao);
        quantidade = findViewById(R.id.quantidadeEditMamadeira);

        dia = mamadeiras.get(position).getDiaInicio();
        mes = mamadeiras.get(position).getMesInico();
        ano = mamadeiras.get(position).getAnoInicio();

        data.setText(String.format("%02d", mamadeiras.get(position).getDiaInicio()) + "/" +
                String.format("%02d", mamadeiras.get(position).getMesInico()) + "/" +
                String.format("%02d", mamadeiras.get(position).getAnoInicio()));

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        EditMamadeirasActivity.this,
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


        horaI.setText(String.format("%02d", mamadeiras.get(position).getHoraInicio()) + ":" +
                String.format("%02d", mamadeiras.get(position).getMinuInicio()) + ":" +
                String.format("%02d", mamadeiras.get(position).getSeguInicio()));

        horaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditMamadeirasActivity.this,
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

        horaT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditMamadeirasActivity.this,
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
                horaT.setText(juncao);
            }
        };


        horaT.setText(String.format("%02d", mamadeiras.get(position).getHoraTermino()) + ":" +
                String.format("%02d", mamadeiras.get(position).getMinuTermino()) + ":" +
                String.format("%02d", mamadeiras.get(position).getSeguTermino()));

        quantidade.setText(Float.toString(mamadeiras.get(position).getQuantidade()));

        if (mamadeiras.get(position).getTomouTudo() == 1) {
            sim.setChecked(true);
        } else {
            nao.setChecked(true);
        }

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void salvaMamadeira(View view) {
        int tudo;
        float quanti;
        quanti = Float.valueOf(quantidade.getText().toString());
        if(sim.isChecked()){
            tudo = 1;
        } else{
            tudo = 0;
        }
        Mamadeiras mamadeiras = new Mamadeiras("Mamadeira",dia,mes,ano,hInicio,mInicio,0,
                dia,mes,ano,hTermino,mTermino,0,quanti,tudo);

        HistoricoSingleton.getInstance().getMamadeiras().set(position, mamadeiras);
        HistoricoSingleton.getInstance().saveMamadeiras(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void excluiMamadeira(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção!!!");
        builder.setMessage("Tem certeza que deseja excluir?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mamadeiras.remove(position);
                HistoricoSingleton.getInstance().saveMamadeiras(EditMamadeirasActivity.this);
                Toast.makeText(EditMamadeirasActivity.this, "Operação concluída!!!", Toast.LENGTH_SHORT).show();
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
