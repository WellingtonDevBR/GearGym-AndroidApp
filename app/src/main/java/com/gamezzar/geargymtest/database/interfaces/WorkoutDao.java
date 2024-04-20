package com.gamezzar.geargymtest.database.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gamezzar.geargymtest.database.entities.Workout;

@Dao
public interface WorkoutDao {
    @Insert
    long insert(Workout workout);

    @Query("SELECT * FROM Workout WHERE Name = :title")
    int getIdByName(String title);
}