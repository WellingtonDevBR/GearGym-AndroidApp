package com.gamezzar.geargymtest.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Workout.class,
                parentColumns = "UID",
                childColumns = "RoutineWorkoutId",
                onDelete = ForeignKey.CASCADE),
},
        indices = {@Index("RoutineWorkoutId")})
public class Set {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    public Integer RoutineWorkoutId;
    public Integer Repetition;
    public Integer Weight;
}
