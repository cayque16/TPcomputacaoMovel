package br.ufop.cayque.mybabycayque.edit;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.Medicamentos;
import br.ufop.cayque.mybabycayque.notificacao.TelaNotificacao;

public class EditMedicamentosActivity extends AppCompatActivity {

    private EditText data, hora, nome, quanti, anotacao;
    private Spinner medida, frequencia;
    private String unidadeSele;
    private static final String[] UNIDADES = {"ml", "g", "colher", "dose", "comprimido", "unidade", "gota"};
    private static final String[] FREQUENCIAS = {"Todo dia", "De 12 em 12 horas", "De 8 em 8 horas", "De 6 em 6 horas", "De 4 em 4 horas"};
    private int dia, mes, ano;
    private int hInicio, mInicio;
    int position;
    private Calendar cal = new GregorianCalendar();
    private DateFormat timeFormat;
    private ArrayList<Medicamentos> medicamentos = HistoricoSingleton.getInstance().getMedicamentos();
    private AlertDialog alerta;
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private TextView textoNotifica;
    private Switch notificar;
    private int frequenciaNotifica = Medicamentos.TODO_DIA;
    private int notifica;
    private AlarmManager alarmManager;

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
        frequencia = findViewById(R.id.spinnerEditMedicamentoFrequencia);
        notificar = findViewById(R.id.switchEditNotificacao);
        textoNotifica = findViewById(R.id.textEditNotifica);

        dia = medicamentos.get(position).getDiaInicio();
        mes = medicamentos.get(position).getMesInico();
        ano = medicamentos.get(position).getAnoInicio();

        cal.set(Calendar.HOUR_OF_DAY, medicamentos.get(position).getHoraInicio());
        cal.set(Calendar.MINUTE, medicamentos.get(position).getMinuInicio());

        timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

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
                String.format("%02d", medicamentos.get(position).getMinuInicio()));

        hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        EditMedicamentosActivity.this,
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

                hora.setText(timeFormat.format(cal.getTime()));
            }
        };

        nome.setText(medicamentos.get(position).getNome());
        quanti.setText(medicamentos.get(position).getDose());
        anotacao.setText(medicamentos.get(position).getAnotacao());
        inicializaSpinnerUnidades();
        String unidade = medicamentos.get(position).getUnidadeMedi();
        for (int i = 0; i < UNIDADES.length; i++) {
            if (unidade.equals(UNIDADES[i])) {
                medida.setSelection(i);
                break;
            }
        }

        notifica = medicamentos.get(position).getNotificacao();
        if (notifica == 1) {
            notificar.setChecked(true);
            textoNotifica.setEnabled(true);
            frequencia.setEnabled(true);
        } else {
            notificar.setChecked(false);
            textoNotifica.setEnabled(false);
            frequencia.setEnabled(false);
        }
        inicializaSpinnerFrequencias();
        frequenciaNotifica = medicamentos.get(position).getFrequenciaNotifica();
        setaSpinnerFrequencias();
    }

    private void setaSpinnerFrequencias() {
        switch (frequenciaNotifica) {
            case Medicamentos.TODO_DIA:
                frequencia.setSelection(0);
                break;
            case Medicamentos.DOZE_EM_DOZE:
                frequencia.setSelection(1);
                break;
            case Medicamentos.OITO_EM_OITO:
                frequencia.setSelection(2);
                break;
            case Medicamentos.SEIS_EM_SEIS:
                frequencia.setSelection(3);
                break;
            case Medicamentos.QUATRO_EM_QUATRO:
                frequencia.setSelection(4);
                break;
        }
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
        Intent it = new Intent(this, TelaNotificacao.class);
        PendingIntent p = PendingIntent.getActivity(this, 0, it, 0);
        int notifica;
        long time = cal.getTimeInMillis();
        if (notificar.isChecked()) {
            notifica = 1;
            alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, time, 1000 * 60 * 24 / frequenciaNotifica, p);
        } else {
            notifica = 0;
            alarmManager.cancel(p);
        }
        Medicamentos medicamentos = new Medicamentos("Medicamento", id, dia, mes, ano, hInicio, mInicio, 0,
                0, nome.getText().toString(), unidadeSele, quanti.getText().toString(), anotacao.getText().toString(), notifica, frequenciaNotifica);

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

    public void habilitaNotificacao(View view) {
        textoNotifica.setEnabled(notificar.isChecked());
        frequencia.setEnabled(notificar.isChecked());
    }

    private void inicializaSpinnerFrequencias() {
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, FREQUENCIAS);
        frequencia.setAdapter(adapterSpinner);
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
}
