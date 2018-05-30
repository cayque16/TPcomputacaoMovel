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
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.add.AddMamadasActivity;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Mamadas;

public class EditMamadasActivity extends AppCompatActivity {

    private EditText data, horaI, horaT;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private int hTermino, mTermino;
    private RadioButton dir, esq, amb;
    int position;
    private ArrayList<Mamadas> mamadas = HistoricoSingleton.getInstance().getMamadas();
    private AlertDialog alerta;
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private TimePickerDialog.OnTimeSetListener timeDialogTermino;

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
        horaT = findViewById(R.id.horaEditMamadaTermino);
        dir = findViewById(R.id.radioEditButtonDirei);
        esq = findViewById(R.id.radioEditButtonEsque);
        amb = findViewById(R.id.radioEditButtonAmbos);

        dia = mamadas.get(position).getDiaInicio();
        mes = mamadas.get(position).getMesInico();
        ano = mamadas.get(position).getAnoInicio();

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
                String.format("%02d", mamadas.get(position).getMinuInicio()) + ":" +
                String.format("%02d", mamadas.get(position).getSeguInicio()));

        horaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditMamadasActivity.this,
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
                        EditMamadasActivity.this,
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


        horaT.setText(String.format("%02d", mamadas.get(position).getHoraTermino()) + ":" +
                String.format("%02d", mamadas.get(position).getMinuTermino()) + ":" +
                String.format("%02d", mamadas.get(position).getSeguTermino()));

        if (mamadas.get(position).getPeito().equals("Direito")) {
            dir.setChecked(true);
        } else if (mamadas.get(position).getPeito().equals("Esquerdo")) {
            esq.setChecked(true);
        } else {
            amb.setChecked(true);
        }

    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void salvaMamada(View view) {
        String peito;
        if (dir.isChecked()) {
            peito = "Direito";
        } else if (esq.isChecked()) {
            peito = "Esquerdo";
        } else {
            peito = "Ambos";
        }
        int id = mamadas.get(position).getId();
        Mamadas mamadas = new Mamadas("Mamada", id, dia, mes, ano, hInicio, mInicio, 0,
                dia, mes, ano, hTermino, mTermino, 0, peito);

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
