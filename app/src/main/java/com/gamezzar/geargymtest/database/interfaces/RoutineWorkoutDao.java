package com.gamezzar.geargymtest.database.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;

import com.gamezzar.geargymtest.database.entities.RoutineWorkout;

@Dao
public interface WorkoutRoutineDao {
    @Insert
    void insert(RoutineWorkout routineWorkout);

    // CRUD operations for WorkoutRoutine
    // Method to retrieve a specific WorkoutRoutine with its sets
}