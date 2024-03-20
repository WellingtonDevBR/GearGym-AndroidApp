package com.gamezzar.geargymtest.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class,
        parentColumns = "UID",
        childColumns = "UserId",
        onDelete = ForeignKey.CASCADE))
public class Routine {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    public Integer UserId;
    public String Name;
    public String DayOfWeek;
}