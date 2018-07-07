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

public class EditMamadasActivity extends AppCompatActivity {

    private EditText data, horaI, duracao, anotacao;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private RadioButton dir, esq, amb;
    int position;
    private Calendar cal = new GregorianCalendar();
    private DateFormat timeFormat;
    private ArrayList<Mamadas> mamadas = HistoricoSingleton.getInstance().getMamadas();
    private AlertDialog alerta;
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_mamadas);

        this.setTitle("Editar mamada");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent it = getIntent();
        position = it.getIntExtra("position", 0);

        data = findViewById(R.id.dataEditMamadaInicio);
        horaI = findViewById(R.id.horaEditaMamadaInicio);
        duracao = findViewById(R.id.horaEditMamadaDuracao);
        dir = findViewById(R.id.radioEditButtonDirei);
        esq = findViewById(R.id.radioEditButtonEsque);
        amb = findViewById(R.id.radioEditButtonAmbos);
        anotacao = findViewById(R.id.anotaEditMamada);

        dia = mamadas.get(position).getDiaInicio();
        mes = mamadas.get(position).getMesInico();
        ano = mamadas.get(position).getAnoInicio();

        cal.set(Calendar.HOUR_OF_DAY, mamadas.get(position).getHoraInicio());
        cal.set(Calendar.MINUTE, mamadas.get(position).getMinuInicio());

        hInicio = cal.get(Calendar.HOUR_OF_DAY);
        mInicio = cal.get(Calendar.MINUTE);

        timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

        data.setText(String.format("%02d", mamadas.get(position).getDiaInicio()) + "/" +
                String.format("%02d", mamadas.get(position).getMesInico()) + "/" +
                String.format("%02d", mamadas.get(position).getAnoInicio()));

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        EditMamadasActivity.this,
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


        horaI.setText(String.format("%02d", mamadas.get(position).getHoraInicio()) + ":" +
                String.format("%02d", mamadas.get(position).getMinuInicio()));

        horaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditMamadasActivity.this,
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


        duracao.setText(Integer.toString(mamadas.get(position).getDuracao()));

        if (mamadas.get(position).getPeito().equals("Direito")) {
            dir.setChecked(true);
        } else if (mamadas.get(position).getPeito().equals("Esquerdo")) {
            esq.setChecked(true);
        } else {
            amb.setChecked(true);
        }

        anotacao.setText(mamadas.get(position).getAnotacao());
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void salvaMamada(View view) {
        String peito;
        int tempoDuracao;
        tempoDuracao = Integer.parseInt(duracao.getText().toString());
        if (dir.isChecked()) {
            peito = "Direito";
        } else if (esq.isChecked()) {
            peito = "Esquerdo";
        } else {
            peito = "Ambos";
        }
        int id = mamadas.get(position).getId();
        Mamadas mamadas = new Mamadas("Mamada", id, dia, mes, ano, hInicio, mInicio, 0,
                tempoDuracao, peito, anotacao.getText().toString());

        HistoricoSingleton.getInstance().getMamadas().set(position, mamadas);
        mamadas.editHistorico(this);
        HistoricoSingleton.getInstance().saveMamadas(this);

        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void excluiMamada(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção!!!");
        builder.setMessage("Tem certeza que deseja excluir?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mamadas.get(position).removeHistorico(EditMamadasActivity.this);
                mamadas.remove(position);

                HistoricoSingleton.getInstance().saveMamadas(EditMamadasActivity.this);

                Toast.makeText(EditMamadasActivity.this, "Operação concluída!!!", Toast.LENGTH_SHORT).show();
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
