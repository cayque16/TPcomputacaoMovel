package br.ufop.cayque.mybabycayque.add;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import br.ufop.cayque.mybabycayque.R;
import br.ufop.cayque.mybabycayque.controllers.HistoricoSingleton;
import br.ufop.cayque.mybabycayque.models.GeraIdSingleton;
import br.ufop.cayque.mybabycayque.models.Mamadas;

public class AddMamadasActivity extends AppCompatActivity {

    private EditText data, horaInicio, duracao, anotacao;
    private RadioGroup radioGroupPeito;
    private int dia, mes, ano;
    private int hInicio, mInicio;
    private Calendar cal = new GregorianCalendar();
    private DatePickerDialog.OnDateSetListener dateDialog;
    private TimePickerDialog.OnTimeSetListener timeDialogInicio;
    private DateFormat dateFormat, timeFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_mamadas);

        this.setTitle("Nova mamada");

        dia = cal.get(Calendar.DAY_OF_MONTH);
        mes = cal.get(Calendar.MONTH) + 1;
        ano = cal.get(Calendar.YEAR);

        hInicio = cal.get(Calendar.HOUR_OF_DAY);
        mInicio = cal.get(Calendar.MINUTE);

        data = findViewById(R.id.dataAddMamadaInicio);
        horaInicio = findViewById(R.id.horaAddMamadaInicio);
        duracao = findViewById(R.id.horaAddMamadaDuracao);
        radioGroupPeito = findViewById(R.id.radioAddGroupPeito);
        anotacao = findViewById(R.id.anotaAddMamada);

        dateFormat = DateFormat.getDateInstance();
        timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);

        data.setText(dateFormat.format(cal.getTime()));
        horaInicio.setText(timeFormat.format(cal.getTime()));

        data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        AddMamadasActivity.this,
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

        horaInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(
                        AddMamadasActivity.this,
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


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
        if (radioGroupPeito.getCheckedRadioButtonId() == R.id.radioAddButtonDirei) {
            peito = "Direito";
        } else if (radioGroupPeito.getCheckedRadioButtonId() == R.id.radioAddButtonEsque) {
            peito = "Esquerdo";
        } else {
            peito = "Ambos";
        }
        int id = GeraIdSingleton.getInstance().geraId(this);
        Mamadas mamadas = new Mamadas("Mamada", id, dia, mes, ano, hInicio, mInicio, 0,
                tempoDuracao, peito, anotacao.getText().toString());

        HistoricoSingleton.getInstance().getMamadas().add(mamadas);
        HistoricoSingleton.getInstance().saveMamadas(this);
        mamadas.addHistorico(this);
        Toast.makeText(this, "Item salvo com sucesso!!!", Toast.LENGTH_SHORT).show();
        finish();
    }
}
