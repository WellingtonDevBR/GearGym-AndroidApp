package com.gamezzar.geargymtest.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BodyPart {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "ImageUri")
    public String ImageUri;
    @ColumnInfo(name = "Details")
    public String Details;
}
