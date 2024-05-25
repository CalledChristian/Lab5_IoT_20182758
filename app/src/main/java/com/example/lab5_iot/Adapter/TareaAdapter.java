package com.example.lab5_iot.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab5_iot.Dao.TareaDao;
import com.example.lab5_iot.Database.TareaDatabase;
import com.example.lab5_iot.Entity.Tarea;
import com.example.lab5_iot.R;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder>{
    private Context context;

    private List<Tarea> listaTareas;



    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    //basado gpt
    /*private OnTareaDeleteListener onTareaDeleteListener;

    public interface OnTareaDeleteListener {
        void onTareaDelete(Tarea tarea);
    }*/
    //



    public class TareaViewHolder extends RecyclerView.ViewHolder{

        Tarea tarea;
        public TareaViewHolder(@NonNull View itemView) {

            super(itemView);

        }

    }

    @NonNull
    @Override
    //Para inflar la vista
    public TareaAdapter.TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new TareaAdapter.TareaViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull TareaAdapter.TareaViewHolder holder, int position) {


        Tarea ta = listaTareas.get(position) ;
        holder.tarea = ta;
        TextView titulo = holder.itemView.findViewById(R.id.textTitle1);
        String tituloStr = ta.getTitulo();
        if (tituloStr.length() > 17) {
            //concatenear titulo hasta 17 caracteres
            tituloStr = tituloStr.substring(0, 17)+"...";
            titulo.setText("Titulo: "+tituloStr);
        }else{
            titulo.setText("Titulo: "+ta.getTitulo());
        }
        TextView descripcion = holder.itemView.findViewById(R.id.textSubtitle1);
        String descripcionStr = ta.getDescripcion();
        if (descripcionStr.length() > 25) {
            descripcionStr = descripcionStr.substring(0, 25)+"...";
            descripcion.setText("Descripcón: "+descripcionStr);
        }else{
            descripcion.setText("Descripción: "+ta.getDescripcion());
        }
        Log.d("msg-test", String.valueOf(descripcion));
        TextView fecha = holder.itemView.findViewById(R.id.textView1);
        fecha.setText("Fecha de Vencimiento: "+dateFormat.format(ta.getFechaVencimiento()));


        TareaDatabase tareaDB = TareaDatabase.getDatabase(getContext());
        TareaDao tareaDao = tareaDB.tareaDao();

        //ImageButton botonEliminar = holder.itemView.findViewById(R.id.boton3);

        holder.itemView.findViewById(R.id.boton3).setOnClickListener(v -> {
                    new Thread((new Runnable() {
                        @Override
                        public void run() {
                            tareaDao.delete(ta);
                        }
                    })).start();
            }
        );


        /*if(botonEliminar.setOnClickListener(view->{
            new Thread(() -> {
                tareaDao.delete(ta);
            }).start();
        }));*/




    }

    @Override
    public int getItemCount() {
        //Este método debe indicar la cantidad total de elementos, en nuestro caso, del
        //arreglo “data”.
        return listaTareas.size();
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Tarea> getListaTareas() {
        return listaTareas;
    }

    public void setListaTareas(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }
}
