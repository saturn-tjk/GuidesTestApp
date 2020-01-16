package com.example.guidestestapp.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.guidestestapp.data.Guide;

import java.util.List;

@androidx.room.Dao
public interface Dao {

    @Query("SELECT * FROM guides")
    List<Guide> getGuides();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertGuide(List<Guide> guides);
}
