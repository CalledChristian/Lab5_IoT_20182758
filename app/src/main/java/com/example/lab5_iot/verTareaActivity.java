package com.example.lab5_iot;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class verTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver_tarea);


        Button recordatorio = findViewById(R.id.button2);
        recordatorio.setOnClickListener(v -> {
            showDateTimePicker();
        });
    }

    //basado en gpt
    private void showDateTimePicker() {
        Calendar currentDate = Calendar.getInstance();
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            Calendar fechaRecordatorio = Calendar.getInstance();
            fechaRecordatorio.set(year, month, dayOfMonth);

            new TimePickerDialog(this, (timePicker, hourOfDay, minute) -> {
                fechaRecordatorio.set(Calendar.HOUR_OF_DAY, hourOfDay);
                fechaRecordatorio.set(Calendar.MINUTE, minute);
                // Aquí puedes manejar el recordatorio con la fecha y hora seleccionadas
                Toast.makeText(verTareaActivity.this, "Recordatorio establecido para: " + fechaRecordatorio.getTime(), Toast.LENGTH_LONG).show();
            }, currentDate.get(Calendar.HOUR_OF_DAY), currentDate.get(Calendar.MINUTE), true).show();
        }, currentDate.get(Calendar.YEAR), currentDate.get(Calendar.MONTH), currentDate.get(Calendar.DAY_OF_MONTH)).show();
    }
}