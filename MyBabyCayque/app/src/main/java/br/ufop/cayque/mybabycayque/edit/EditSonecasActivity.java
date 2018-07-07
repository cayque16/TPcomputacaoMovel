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
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Sonecas;

public class EditSonecasActivity extends AppCompatActivity {

    private EditText dataI, horaInicio, duracao, anotacao;
    private int diaI, mesI, anoI;
    private int hInicio, mInicio;
    int position;
    private Calendar cal = new GregorianCalendar();
    private DateFormat timeFormat;
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
        horaInicio = findViewById(R.id.horaEditSonecaInicio);
        duracao = findViewById(R.id.horaEditSonecaDuracao);
        anotacao = findViewById(R.id.anotaEditSoneca);

        diaI = sonecas.get(position).getDiaInicio();
        mesI = sonecas.get(position).getMesInico();
        anoI = sonecas.get(position).getAnoInicio();

        cal.set(Calendar.HOUR_OF_DAY, sonecas.get(position).getHoraInicio());
        cal.set(Calendar.MINUTE, sonecas.get(position).getMinuInicio());

        hInicio = cal.get(Calendar.HOUR_OF_DAY);
        mInicio = cal.get(Calendar.MINUTE);

        timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

        dataI.setText(String.format("%02d", sonecas.get(position).getDiaInicio()) + "/" +
                String.format("%02d", sonecas.get(position).getMesInico()) + "/" +
                String.format("%02d", sonecas.get(position).getAnoInicio()));

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


        horaInicio.setText(String.format("%02d", sonecas.get(position).getHoraInicio()) + ":" +
                String.format("%02d", sonecas.get(position).getMinuInicio()));

        horaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditSonecasActivity.this,
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

        duracao.setOnClickListener(new View.OnClickListener() {
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

        duracao.setText(Integer.toString(sonecas.get(position).getDuracao()));

        anotacao.setText(sonecas.get(position).getAnotacao());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void salvaSoneca(View view) {
        int tempoDuracao;
        tempoDuracao = Integer.parseInt(duracao.getText().toString());
        int id = sonecas.get(position).getId();
        Sonecas sonecas = new Sonecas("Soneca", id, diaI, mesI, anoI, hInicio, mInicio, 0,
                tempoDuracao, 0, anotacao.getText().toString());

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
