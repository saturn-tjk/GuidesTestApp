package com.example.guidestestapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.guidestestapp.data.Guide;

@Database(entities = {Guide.class}, version = 1, exportSchema = false)
public abstract class LocalData extends RoomDatabase {

    private static LocalData INSTANCE;

    public abstract Dao dao();

    public static LocalData getInstance(Context context){

        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), LocalData.class, "guides_db").build();
        }
        return INSTANCE;
    }
}
