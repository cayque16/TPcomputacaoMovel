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
import br.ufop.cayque.mybabycayque.models.Mamadas;
import br.ufop.cayque.mybabycayque.models.Mamadeiras;

public class EditMamadeirasActivity extends AppCompatActivity {

    private EditText data, horaI, horaT, quantidade, anotacao;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private int hTermino, mTermino;
    private RadioButton sim, nao;
    int position;
    private Calendar cal = new GregorianCalendar();
    private DateFormat timeFormat;
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
        anotacao = findViewById(R.id.anotaEditMamadeira);

        dia = mamadeiras.get(position).getDiaInicio();
        mes = mamadeiras.get(position).getMesInico();
        ano = mamadeiras.get(position).getAnoInicio();

        cal.set(Calendar.HOUR_OF_DAY, mamadeiras.get(position).getHoraInicio());
        cal.set(Calendar.MINUTE, mamadeiras.get(position).getMinuInicio());

        timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

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
                String.format("%02d", mamadeiras.get(position).getMinuInicio()));

        horaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditMamadeirasActivity.this,
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

        anotacao.setText(mamadeiras.get(position).getAnotacao());
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
        if (sim.isChecked()) {
            tudo = 1;
        } else {
            tudo = 0;
        }
        int id = mamadeiras.get(position).getId();
        Mamadeiras mamadeiras = new Mamadeiras("Mamadeira", id, dia, mes, ano, hInicio, mInicio, 0,
                dia, mes, ano, hTermino, mTermino, 0, quanti, tudo, anotacao.getText().toString());

        HistoricoSingleton.getInstance().getMamadeiras().set(position, mamadeiras);
        HistoricoSingleton.getInstance().saveMamadeiras(this);
        mamadeiras.editHistorico(this);
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
                mamadeiras.get(position).removeHistorico(EditMamadeirasActivity.this);
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
