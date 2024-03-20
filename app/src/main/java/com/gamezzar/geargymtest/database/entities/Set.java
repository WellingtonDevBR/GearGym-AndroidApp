package com.gamezzar.geargymtest.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = WorkoutRoutine.class, parentColumns = "UID", childColumns = "WorkoutRoutineId", onDelete = ForeignKey.CASCADE)
})
public class Set {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    public Integer WorkoutRoutineId;
    public Integer Weight;
}