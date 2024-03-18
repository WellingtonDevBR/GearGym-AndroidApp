package com.gamezzar.geargymtest.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Workout.class, parentColumns = "UID", childColumns = "WorkoutId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = BodyPart.class, parentColumns = "UID", childColumns = "BodyPart", onDelete = ForeignKey.CASCADE),
})
public class WorkoutBodyPart {
    public Integer WorkoutId;
    public Integer BodyPartId;
}
