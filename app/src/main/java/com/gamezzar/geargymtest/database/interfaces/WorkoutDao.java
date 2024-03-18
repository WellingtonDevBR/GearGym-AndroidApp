package com.gamezzar.geargymtest.database.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;

import com.gamezzar.geargymtest.database.entities.Workout;

@Dao
public interface WorkoutDao {
    @Insert
    void insert(Workout workout);
}