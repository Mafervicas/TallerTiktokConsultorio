package com.example.taller2_consulta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Calendar;

public class SegundaPantalla extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TimePicker.OnTimeChangedListener, View.OnClickListener {

    //Inicializar las variables
    private final Calendar calendar = Calendar.getInstance();
    Button boton2;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSatListener;
    private Spinner spinner;
    private String opcionSeleccionada = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_pantalla);
        getSupportActionBar().hide();

        //Boton Toast
        boton2 = (Button) findViewById(R.id.btAgendar);
        boton2.setOnClickListener(this);

        //Time Picker
        TimePicker timePicker = findViewById(R.id.time_picker);
        timePicker.setOnTimeChangedListener(this);

        //Spinner
        spinner = findViewById(R.id.spinner);
        //Traite lo que hay dentro del arreglo
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.padecimiento, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        //TetView
        mDisplayDate = (TextView) findViewById(R.id.tvFecha);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Calendario
                Calendar cal= Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                //Esto es para el estilo y el tema
                DatePickerDialog dialog = new DatePickerDialog(
                        SegundaPantalla.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSatListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable( new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

                //Poner formato
        mDateSatListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month = month + 1;

                String date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

    }


    //Método Spinner
    //Algo seleccionado
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if( parent.equals(spinner)){
            opcionSeleccionada = parent.getItemAtPosition(position).toString();
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.equals(spinner)){
            opcionSeleccionada= "";
        }

    }

    //Metodo del Tiempo
    @Override
    public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
        //Fijar hora y minutos
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);

    }

    //Metodos para mostrar en Toast
    @Override
    public void onClick(View v) {
        String date = mDisplayDate.getText().toString();
        //Pop up
        Toast.makeText(SegundaPantalla.this, "Cita agendada para: "+ date + " .Causa: "+ opcionSeleccionada + ". Gracias por agendar", Toast.LENGTH_LONG).show();
    }
}