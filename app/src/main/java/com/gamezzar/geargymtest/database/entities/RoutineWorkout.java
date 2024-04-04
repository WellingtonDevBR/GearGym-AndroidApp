package com.gamezzar.geargymtest.database.entities;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(indices = {
        @Index(value = {"RoutineId", "WorkoutId"}, unique = true),
        @Index(value = "WorkoutId")
})
public class RoutineWorkout {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public int UID;

    @NonNull
    public Integer RoutineId;

    @NonNull
    public Integer WorkoutId;
}