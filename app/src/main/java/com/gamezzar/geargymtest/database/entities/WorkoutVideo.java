package com.gamezzar.geargymtest.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Workout.class, parentColumns = "UID", childColumns = "WorkoutId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = Video.class, parentColumns = "UID", childColumns = "VideoId", onDelete = ForeignKey.CASCADE),
})
public class WorkoutVideo {
    public Integer WorkoutId;
    public Integer VideoID;
}
