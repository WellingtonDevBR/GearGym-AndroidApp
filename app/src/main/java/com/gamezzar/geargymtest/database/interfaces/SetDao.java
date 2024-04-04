package com.gamezzar.geargymtest.database.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gamezzar.geargymtest.database.entities.Set;

@Dao
public interface SetDao {
    @Insert
    long insert(Set set);

    @Query("DELETE FROM 'Set' WHERE RoutineWorkoutId = :routineWorkoutId")
    void deleteSetsByRoutineWorkoutId(int routineWorkoutId);
}