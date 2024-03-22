package com.gamezzar.geargymtest.database.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.gamezzar.geargymtest.database.entities.BodyPart;
import com.gamezzar.geargymtest.database.entities.Workout;

import java.util.List;

public class BodyPartWithWorkouts {

    @Embedded
    public BodyPart bodyPart;

    @Relation(parentColumn = "UID", entityColumn = "BodyPartId", entity = Workout.class)
    public List<Workout> workouts;
}
