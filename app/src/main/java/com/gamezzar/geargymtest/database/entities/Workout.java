package com.gamezzar.geargymtest.database.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(
                entity = BodyPart.class,
                parentColumns = "UID",
                childColumns = "BodyPartId",
                onDelete = ForeignKey.CASCADE
        )
})
public class Workout {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    public String Name;
    public Integer BodyPartId;
    public String ImageUri;
    @Nullable
    public String Details;
    @Nullable
    public String Duration;
}
