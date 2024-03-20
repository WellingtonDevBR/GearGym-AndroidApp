package com.gamezzar.geargymtest.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class BodyPart {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    public String Name;
    public String ImageUri;
}