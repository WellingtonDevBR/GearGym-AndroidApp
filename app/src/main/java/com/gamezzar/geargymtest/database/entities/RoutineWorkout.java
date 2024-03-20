package com.gamezzar.geargymtest.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(primaryKeys = {"RoutineId", "WorkoutId"})
public class WorkoutRoutine {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    public Integer RoutineId;
    public Integer WorkoutId;
}
