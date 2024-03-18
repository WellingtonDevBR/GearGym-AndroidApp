package com.gamezzar.geargymtest.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = User.class, parentColumns = "UID", childColumns = "UserId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Workout.class, parentColumns = "UID", childColumns = "WorkoutId", onDelete = ForeignKey.CASCADE),
})
public class UserWorkout {

    public Integer UserId;
    public Integer WorkoutId;
}
