package com.example.lab5_iot;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.lab5_iot.Dao.TareaDao;
import com.example.lab5_iot.Database.TareaDatabase;
import com.example.lab5_iot.Entity.Tarea;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AnadirTareaActivity extends AppCompatActivity {

    private EditText titulo;
    private EditText descripcion;
    private Button botonFecha;

    private Button botonGuardar;


    private Context context;

    private TareaDatabase tareaDB;

    private TareaDao tareaDao;

    private Date fechaTarea;

    private TextView fecha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anadir_tarea);

        fecha = findViewById(R.id.textView6);

        botonFecha = findViewById(R.id.boton);

        botonGuardar = findViewById(R.id.button);



        botonFecha.setOnClickListener(v ->
            fechaDialog()
        );

        botonGuardar.setOnClickListener(v ->
                guardarTarea());

    }

    //basado en gpt
    private void fechaDialog() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    fechaTarea = calendar.getTime();
                    fecha.setText(fechaTarea.toString());
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    public void guardarTarea() {

        titulo = findViewById(R.id.editTextText4);
        descripcion = findViewById(R.id.editTextText5);



        String tituloStr = titulo.getEditableText().toString();
        String descripcionStr = descripcion.getEditableText().toString();
        //seccion basado en gpt
        if (fechaTarea == null) {
            fecha.setError("Seleccione una fecha de vencimiento");
            return;
        }
        Tarea tarea = new Tarea(tituloStr, descripcionStr, fechaTarea);
        //Insertar tarea
        tareaDB = TareaDatabase.getDatabase(getApplicationContext());
        tareaDao = tareaDB.tareaDao();
        new Thread(() -> {
            tareaDao.insert(tarea);
            Log.d("msg-test4", "tarea guarda");
            runOnUiThread(this::finish);
        }).start();

        //regresar a lista de tareas
        Intent intent = new Intent(this, HomeActivity.class);
        //iniciar activity
        startActivity(intent);

    }


    /*public void guardarSupervisor() throws ParseException {

        titulo = findViewById(R.id.editTextText4);
        descripcion = findViewById(R.id.editTextText5);
        fechaVencimiento = findViewById(R.id.editTextText6);

        String tituloStr = titulo.getEditableText().toString();
        String descripcionStr = descripcion.getEditableText().toString();
        //seccion basado en gpt
        String fechaVencimientoStr = fechaVencimiento.getEditableText().toString().trim();
        SimpleDateFormat fecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        Date fechaVencimientoTarea = fecha.parse(fechaVencimientoStr);
        Tarea tarea = new Tarea(tituloStr, descripcionStr, fechaVencimientoTarea);
        //nombre del archivo a guardar
        String fileName = "listaTareas";
        ArrayList<Tarea> listaTareasOF = new ArrayList<>();
        listaTareasOF.add(tarea);
        //Se utiliza la clase FileOutputStream para poder almacenar en Android
        try (FileOutputStream fileOutputStream = this.openFileOutput(fileName, Context.MODE_PRIVATE);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            //con objectOutputStream se realiza la escritura como objeto
            objectOutputStream.writeObject(listaTareasOF);
            Log.d("msg-test", "tarea guardada");
            Intent intent = new Intent(this, HomeActivity.class); // Reemplaza "TuActivity" con el nombre de tu Activity
            startActivity(intent);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("msg-test", "error al guardar tarea", e);
            Toast.makeText(this, "error al guardar tarea", Toast.LENGTH_LONG).show();
        }
    }*/

    /*public void listarArchivosGuardados(){
        String[] archivosGuardados = fileList();

        for(String archivo: archivosGuardados){
            Log.d("archivo",archivo);
        }
    }

    public void leerArchivoObjeto() {
        String fileName = "listaSupervisores";
        try (FileInputStream fileInputStream = openFileInput(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            ArrayList<Tarea> arregloTareas = (ArrayList<Tarea>) objectInputStream.readObject();
            for(Tarea t: arregloTareas ){
                Log.d("super:",t.getTitulo());
            }
        } catch (FileNotFoundException | ClassNotFoundException e) {
            e.printStackTrace();
            Log.e("super", "Error al leer el supervisor guardado", e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }*/
}