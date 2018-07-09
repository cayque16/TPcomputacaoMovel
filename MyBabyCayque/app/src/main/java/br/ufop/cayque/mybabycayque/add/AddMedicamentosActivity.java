package br.ufop.cayque.mybabycayque.add;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import java.util.TimeZone;

import br.ufop.cayque.mybabycayque.MainActivity;
import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.GeraIdSingleton;
import br.ufop.cayque.mybabycayque.models.Medicamentos;
import br.ufop.cayque.mybabycayque.notificacao.NotificacaoActivity;

public class AddMedicamentosActivity extends AppCompatActivity {

    private EditText data, hora, nome, quanti, anotacao;
    private Spinner medida, frequencia;
    private String unidadeSele;
    private static final String[] UNIDADES = {"ml", "g", "colher", "dose", "comprimido", "unidade", "gota"};
    private static final String[] FREQUENCIAS = {"Todo dia", "De 12 em 12 horas", "De 8 em 8 horas", "De 6 em 6 horas", "De 4 em 4 horas"};
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private Calendar cal;
    private DateFormat dateFormat, timeFormat;
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog timeDialogInicio;
    private Switch notificar;
    private int frequenciaNotifica = Medicamentos.TODO_DIA;
    private TextView textoNotifica;
    private AlarmManager alarmManager;
    private long somaFrequencia = 86400;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_medicamentos);

        this.setTitle("Novo medicamento");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cal = new GregorianCalendar();
        cal.setTimeZone(TimeZone.getDefault());

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
        timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

        data.setText(dateFormat.format(cal.getTime()));
        hora.setText(timeFormat.format(cal.getTime()));

        hInicio = cal.get(Calendar.HOUR_OF_DAY);
        mInicio = cal.get(Calendar.MINUTE);

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
                timeDialogInicio.show();
            }
        });

        timeDialogInicio = new TimePickerDialog(this, AlertDialog.THEME_HOLO_LIGHT,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        cal.set(Calendar.HOUR_OF_DAY, i);
                        cal.set(Calendar.MINUTE, i1);
                        hInicio = cal.get(Calendar.HOUR_OF_DAY);
                        mInicio = cal.get(Calendar.MINUTE);
                        hora.setText(timeFormat.format(cal.getTime()));
                    }
                },
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true);
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
                        somaFrequencia = 86400;
                        break;
                    case 1:
                        frequenciaNotifica = Medicamentos.DOZE_EM_DOZE;
                        somaFrequencia = 43200;
                        break;
                    case 2:
                        frequenciaNotifica = Medicamentos.OITO_EM_OITO;
                        somaFrequencia = 28800;
                        break;
                    case 3:
                        frequenciaNotifica = Medicamentos.SEIS_EM_SEIS;
                        somaFrequencia = 21600;
                        break;
                    case 4:
                        frequenciaNotifica = Medicamentos.QUATRO_EM_QUATRO;
                        somaFrequencia = 14400;
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
        Intent it = new Intent(this, NotificacaoActivity.class);
        PendingIntent p = PendingIntent.getActivity(this, 0, it, 0);
        int notifica;
        long time = (cal.getTimeInMillis() ) + (somaFrequencia * 1000);
        long diferenca = (time - System.currentTimeMillis()) / 1000;
        if (notificar.isChecked()) {
            notifica = 1;
            alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 1000 * 3600 * 24 / frequenciaNotifica, p);
        } else {
            notifica = 0;
        }
        Medicamentos medicamentos = new Medicamentos("Medicamento", id, dia, mes, ano, hInicio, mInicio, 0,
                0, nome.getText().toString(), unidadeSele, quanti.getText().toString(), anotacao.getText().toString(),
                notifica, frequenciaNotifica);

        HistoricoSingleton.getInstance().getMedicamentos().add(medicamentos);
        HistoricoSingleton.getInstance().saveMedicamentos(this);
        medicamentos.addHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }

    public void habilitaNotificacao(View view) {
        textoNotifica.setEnabled(notificar.isChecked());
        frequencia.setEnabled(notificar.isChecked());
        if (!notificar.isChecked())
            frequencia.setSelection(0);
    }
}