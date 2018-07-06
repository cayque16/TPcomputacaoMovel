package br.ufop.cayque.mybabycayque.add;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.GeraIdSingleton;
import br.ufop.cayque.mybabycayque.models.Medicamentos;

public class AddMedicamentosActivity extends AppCompatActivity {

    private EditText data, hora, nome, quanti, anotacao;
    private Spinner medida, frequencia;
    private String unidadeSele;
    private static final String[] UNIDADES = {"ml", "g", "colher", "dose", "comprimido", "unidade", "gota"};
    private static final String[] FREQUENCIAS = {"Todo dia", "De 12 em 12 horas", "De 8 em 8 horas", "De 6 em 6 horas", "De 4 em 4 horas"};
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private Calendar cal = new GregorianCalendar();
    private DateFormat dateFormat, timeFormat;
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private Switch notificar;
    private int frequenciaNotifica = 0;
    private TextView textoNotifica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicamentos);

        this.setTitle("Novo medicamento");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        ano = cal.get(Calendar.YEAR);

        data = findViewById(R.id.dataAddMedicamentoInicio);
        hora = findViewById(R.id.horaAddMedicamentoInicio);
        nome = findViewById(R.id.nomeAddMedicamento);
        quanti = findViewById(R.id.quantidadeAddMedicamento);
        medida = findViewById(R.id.spinnerAddMedicamentoMedidada);
        anotacao = findViewById(R.id.anotaAddMedicamento);
        frequencia = findViewById(R.id.spinnerAddMedicamentoFrequencia);
        notificar = findViewById(R.id.switchAddNotificacao);
        textoNotifica = findViewById(R.id.textAddNotifica);

        inicializaSpinnerUnidades();
        inicializaSpinnerFrequencias();

        dateFormat = DateFormat.getDateInstance();
        timeFormat = DateFormat.getTimeInstance();

        data.setText(dateFormat.format(cal.getTime()));
        hora.setText(timeFormat.format(cal.getTime()));

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddMedicamentosActivity.this,
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

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        AddMedicamentosActivity.this,
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
    }

    private void inicializaSpinnerUnidades() {
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

    private void inicializaSpinnerFrequencias() {
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, FREQUENCIAS);
        frequencia.setAdapter(adapterSpinner);
        frequencia.setEnabled(false); //começa com o spinner desabilitado
        frequencia.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        frequenciaNotifica = Medicamentos.TODO_DIA;
                        break;
                    case 1:
                        frequenciaNotifica = Medicamentos.DOZE_EM_DOZE;
                        break;
                    case 2:
                        frequenciaNotifica = Medicamentos.OITO_EM_OITO;
                        break;
                    case 3:
                        frequenciaNotifica = Medicamentos.SEIS_EM_SEIS;
                        break;
                    case 4:
                        frequenciaNotifica = Medicamentos.QUATRO_EM_QUATRO;
                        break;
                    default:
                        frequenciaNotifica = 0;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void salvaMedicamento(View view) {
        int id = GeraIdSingleton.getInstance().geraId(this);
        int notifica;
        if (notificar.isChecked()) {
            notifica = 1;
        } else {
            notifica = 0;
        }
        Medicamentos medicamentos = new Medicamentos("Medicamento", id, dia, mes, ano, hInicio, mInicio, 0,
                dia, mes, ano, hInicio, mInicio, 0, nome.getText().toString(), unidadeSele, quanti.getText().toString(), anotacao.getText().toString(), notifica, frequenciaNotifica);

        HistoricoSingleton.getInstance().getMedicamentos().add(medicamentos);
        HistoricoSingleton.getInstance().saveMedicamentos(this);
        medicamentos.addHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void habilitaNotificacao(View view) {
        textoNotifica.setEnabled(notificar.isChecked());
        frequencia.setEnabled(notificar.isChecked());
    }
}