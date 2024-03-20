package com.gamezzar.geargymtest.database.entities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(primaryKeys = {"RoutineId", "WorkoutId"},
        indices = {
                @Index(value = "UID", unique = true),
                @Index(value = "WorkoutId")
        })
public class RoutineWorkout {
    public Integer UID;
    @NonNull
    public Integer RoutineId;
    @NonNull
    public Integer WorkoutId;
}
