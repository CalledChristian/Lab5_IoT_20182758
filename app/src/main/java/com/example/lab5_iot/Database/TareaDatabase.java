package com.example.lab5_iot.Database;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import android.content.Context;

import com.example.lab5_iot.Converter.Converters;
import com.example.lab5_iot.Dao.TareaDao;
import com.example.lab5_iot.Entity.Tarea;

@Database(entities = {Tarea.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class TareaDatabase extends RoomDatabase {

    public abstract TareaDao tareaDao();

    private static TareaDatabase INSTANCE;

    public static TareaDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TareaDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    TareaDatabase.class, "tarea_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}

