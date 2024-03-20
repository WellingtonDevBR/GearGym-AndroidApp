package com.gamezzar.geargymtest.database.models;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.gamezzar.geargymtest.database.entities.Routine;
import com.gamezzar.geargymtest.database.entities.Workout;
import com.gamezzar.geargymtest.database.entities.WorkoutRoutine;

import java.util.List;

public class RoutineWithWorkoutsAndSets {
    @Embedded public Routine routine;
    @Relation(
            entity = Workout.class,
            parentColumn = "UID",
            entityColumn = "UID",
            associateBy = @Junction(
                    value = WorkoutRoutine.class,
                    parentColumn = "RoutineId",
                    entityColumn = "WorkoutId"
            )
    )
    public List<WorkoutWithSets> workouts;
}