package com.gamezzar.geargymtest.database.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Workout {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "ImageUri")
    public String ImageUri;
    @ColumnInfo(name = "Details")
    public String Details;
    @Nullable
    @ColumnInfo(name = "Duration")
    public String Duration;
}
