package com.gamezzar.geargymtest.database.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.gamezzar.geargymtest.database.entities.Routine;
import com.gamezzar.geargymtest.database.models.RoutineWithWorkoutsAndSets;

import java.util.List;

@Dao
public interface RoutineDao {
    @Insert
    void insert(Routine routine);

    @Insert
    void insertRoutineWorkoutSetCrossRef(RoutineWorkoutSetCrossRef crossRef);

    // This is a simplified version; you may need a custom method to process and combine data
    @Transaction
    @Query("SELECT * FROM Routine WHERE UID = :routineId")
    LiveData<List<RoutineWithWorkoutsAndSets>> getRoutineWithWorkoutsAndSets(int routineId);
}