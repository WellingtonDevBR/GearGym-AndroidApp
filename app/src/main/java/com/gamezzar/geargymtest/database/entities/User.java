package com.gamezzar.geargymtest.database.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int UID;
    public String Name;
    public String Email;
    public String Password;
    public int Age;
}
