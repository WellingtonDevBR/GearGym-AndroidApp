package com.gamezzar.geargymtest.database.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int UID;
    @ColumnInfo(name = "Name")
    public String Name;
    @ColumnInfo(name = "Email")
    public String Email;
    @ColumnInfo(name = "Password")
    public String Password;
    @ColumnInfo(name = "Age")
    public int Age;
}
