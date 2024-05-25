package com.example.lab5_iot.Dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.lab5_iot.Entity.Tarea;

import java.util.List;

@Dao
public interface TareaDao {
    @Insert
    public void insert(Tarea tarea);

    @Update
    public void update(Tarea tarea);

    @Delete
    public void delete(Tarea tarea);

    @Query("SELECT * FROM tarea")
    public List<Tarea> obtenerTareas();

}
