package com.gamezzar.geargymtest.database.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gamezzar.geargymtest.database.entities.RoutineWorkout;

import java.util.List;

@Dao
public interface RoutineWorkoutDao {
    @Insert
    long insert(RoutineWorkout routineWorkout);

    @Query("DELETE FROM RoutineWorkout WHERE RoutineId = :routineId")
    void deleteByRoutineId(int routineId);

    // Assuming you need this method to get RoutineWorkouts by RoutineId
    @Query("SELECT * FROM RoutineWorkout WHERE RoutineId = :routineId")
    List<RoutineWorkout> findRoutineWorkoutsByRoutineId(int routineId);
}