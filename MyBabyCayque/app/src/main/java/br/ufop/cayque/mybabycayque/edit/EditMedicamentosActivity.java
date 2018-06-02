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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Medicamentos;

public class EditMedicamentosActivity extends AppCompatActivity {

    private EditText data, hora, nome, quanti,anotacao;
    private Spinner medida;
    private String unidadeSele;
    private static final String[] UNIDADES = {"ml", "g", "colher", "dose", "comprimido", "unidade", "gota"};
    private int dia, mes, ano;
    private int hInicio, mInicio;
    int position;
    private ArrayList<Medicamentos> medicamentos = HistoricoSingleton.getInstance().getMedicamentos();
    private AlertDialog alerta;
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private TimePickerDialog.OnTimeSetListener timeDialogTermino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_medicamentos);

        this.setTitle("Editar medicamento");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Intent it = getIntent();
        position = it.getIntExtra("position", 0);

        data = findViewById(R.id.dataEditMedicamentoInicio);
        hora = findViewById(R.id.horaEditMedicamentoInicio);
        nome = findViewById(R.id.nomeEditMedicamento);
        quanti = findViewById(R.id.quantidadeEditMedicamento);
        medida = findViewById(R.id.spinnerEditMedicamentoMedidada);
        anotacao = findViewById(R.id.anotaEditMedicamento);

        dia = medicamentos.get(position).getDiaInicio();
        mes = medicamentos.get(position).getMesInico();
        ano = medicamentos.get(position).getAnoInicio();

        data.setText(String.format("%02d", medicamentos.get(position).getDiaInicio()) + "/" +
                String.format("%02d", medicamentos.get(position).getMesInico()) + "/" +
                String.format("%02d", medicamentos.get(position).getAnoInicio()));

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        EditMedicamentosActivity.this,
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


        hora.setText(String.format("%02d", medicamentos.get(position).getHoraInicio()) + ":" +
                String.format("%02d", medicamentos.get(position).getMinuInicio()) + ":" +
                String.format("%02d", medicamentos.get(position).getSeguInicio()));

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditMedicamentosActivity.this,
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
                hora.setText(juncao);
            }
        };

        nome.setText(medicamentos.get(position).getNome());
        quanti.setText(medicamentos.get(position).getDose());
        anotacao.setText(medicamentos.get(position).getAnotacao());
        inicializaSpinner();

    }

    private void inicializaSpinner() {
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, UNIDADES);
        medida.setAdapter(adapterSpinner);
        medida.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                unidadeSele = UNIDADES[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void excluiMedicamento(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Atenção!!!");
        builder.setMessage("Tem certeza que deseja excluir?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                medicamentos.get(position).removeHistorico(EditMedicamentosActivity.this);
                medicamentos.remove(position);

                HistoricoSingleton.getInstance().saveMedicamentos(EditMedicamentosActivity.this);

                Toast.makeText(EditMedicamentosActivity.this, "Operação concluída!!!", Toast.LENGTH_SHORT).show();
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

    public void salvaMedicamento(View view) {
        int id = medicamentos.get(position).getId();
        Medicamentos medicamentos = new Medicamentos("Medicamento", id, dia, mes, ano, hInicio, mInicio, 0,
                dia, mes, ano, hInicio, mInicio, 0, nome.getText().toString(), unidadeSele, quanti.getText().toString(),anotacao.getText().toString());

        HistoricoSingleton.getInstance().getMedicamentos().set(position, medicamentos);
        HistoricoSingleton.getInstance().saveMedicamentos(this);
        medicamentos.editHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
