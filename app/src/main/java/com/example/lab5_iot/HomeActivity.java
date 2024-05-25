package com.example.lab5_iot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_iot.Adapter.TareaAdapter;
import com.example.lab5_iot.Dao.TareaDao;
import com.example.lab5_iot.Database.TareaDatabase;
import com.example.lab5_iot.Entity.Tarea;
import com.example.lab5_iot.databinding.ActivityHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {



    private TareaDatabase tareaDB;
    private TareaDao tareaDao;
    private List<Tarea> listaTareas;

    private TareaAdapter tareaAdapter;

    private LinearLayoutManager layoutManager;


    private RecyclerView recyclerView;

    private ImageButton botonEliminar;

    private Context context;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        tareaAdapter = new TareaAdapter();
        recyclerView = findViewById(R.id.recyclerview_tareas);

        // Obtenemos la instancia de base de datos
        tareaDB = TareaDatabase.getDatabase(getApplicationContext());
        tareaDao = tareaDB.tareaDao();



        //listaTareas = new ArrayList<>();
        //listaTareas.add(new Tarea("HOLA","VAYA VAYA",20/05/2024));

        //cargarTareas();

        String cod = getIntent().getStringExtra("codigo");
        Log.d("cod",cod);
            new Thread(() -> {
                listaTareas = tareaDao.obtenerTareasPorCodigo(cod);
                Log.d("entra?1","si");
                runOnUiThread(() -> {
                            Log.d("entra?2", "si");
                            // Actualizar la UI con las tareas+
                            if (!listaTareas.isEmpty()) {
                                Log.d("listavacia?", "no");
                                tareaAdapter.setContext(context);
                                tareaAdapter.setListaTareas(listaTareas);
                                Log.d("llega hasta aca0?", "si");
                                recyclerView.setAdapter(tareaAdapter);
                                Log.d("llega hasta aca1?", "si");
                                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                                Log.d("llega hasta aca2?", "si");
                            }
                        });
                }).start();

    }
                        /*for(Tarea tarea: listaTareas){
                            botonEliminar.setOnClickListener(view->{
                                new Thread(() -> {
                                    tareaDao.delete(tarea);
                                    Log.d("msg-test4", "tarea guarda");
                                    runOnUiThread(this::finish);
                                }).start();
                            });
                        }
                    }


                });
            }).start();*/






    /*public void cargarTareas() {

        new Thread(() -> {
            listaTareas = tareaDao.obtenerTareas();
            Log.d("entra?1","si");
            runOnUiThread(() -> {
                Log.d("entra?2","si");
                // Actualizar la UI con las tareas+
                if(!listaTareas.isEmpty()) {
                    Log.d("listavacia?","no");
                    tareaAdapter.setContext(context);
                    tareaAdapter.setListaTareas(listaTareas);
                    Log.d("llega hasta aca0?","si");
                    recyclerView.setAdapter(tareaAdapter);
                    Log.d("llega hasta aca1?","si");
                    recyclerView.setLayoutManager(new LinearLayoutManager(this));
                    Log.d("llega hasta aca2?","si");
                        /*for(Tarea tarea: listaTareas){
                            botonEliminar.setOnClickListener(view->{
                                new Thread(() -> {
                                    tareaDao.delete(tarea);
                                    Log.d("msg-test4", "tarea guarda");
                                    runOnUiThread(this::finish);
                                }).start();
                            });
                        }
                }

            });
        }).start();
    }*/

        /*geoAdapter.setContext(getContext());
        geoAdapter.setListaGeo(listaGeo);
        binding.recyclerviewGeolocalizacion.setAdapter(geoAdapter);
        binding.recyclerviewGeolocalizacion.setLayoutManager(new LinearLayoutManager(getContext()));*/




    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            listarTareas();
        }
    }


    private void listarTareas {
        new Thread(() -> {
            List<Task> tasks = taskDao.getAllTasks();
            runOnUiThread(() -> taskAdapter.setTasks(tasks));
        }).start();
    } */

    public void irAnadirTareaActivity(View view) {

        Intent intent = new Intent(this, AnadirTareaActivity.class);
        String cod = getIntent().getStringExtra("codigo");
        intent.putExtra("codigo",cod);
        //iniciar activity
        startActivity(intent);


    }


}