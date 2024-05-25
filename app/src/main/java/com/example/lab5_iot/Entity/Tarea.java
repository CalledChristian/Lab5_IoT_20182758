package com.example.lab5_iot.Entity;

import android.widget.DatePicker;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;
import java.util.Date;

@Entity(tableName = "tarea")
public class Tarea implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private Integer idTarea;
    private String titulo;
    private String descripcion;

    private Date fechaVencimiento;

    public Tarea (String titulo,
                  String descripcion,Date fechaVencimiento){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaVencimiento = fechaVencimiento;
    }


    public Integer getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(Integer idTarea) {
        this.idTarea = idTarea;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }
}
