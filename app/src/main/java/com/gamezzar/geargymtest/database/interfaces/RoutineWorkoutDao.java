package com.gamezzar.geargymtest.database.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;

import com.gamezzar.geargymtest.database.entities.RoutineWorkout;

@Dao
public interface RoutineWorkoutDao {
    @Insert
    void insert(RoutineWorkout routineWorkout);

}