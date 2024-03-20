package com.gamezzar.geargymtest.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Equipment {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    public String Name;
    public String Details;
    public String ImageUri;
}