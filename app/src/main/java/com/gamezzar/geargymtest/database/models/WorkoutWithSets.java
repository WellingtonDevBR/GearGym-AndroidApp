package com.gamezzar.geargymtest.database.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.gamezzar.geargymtest.database.entities.Set;
import com.gamezzar.geargymtest.database.entities.Workout;

import java.util.List;

public class WorkoutWithSets {
    @Embedded
    public Workout workout;
    @Relation(
            parentColumn = "UID",
            entityColumn = "WorkoutRoutineId"
    )
    public List<Set> sets;
}