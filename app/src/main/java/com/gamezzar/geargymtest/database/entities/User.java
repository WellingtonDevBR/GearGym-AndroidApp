package com.gamezzar.geargymtest.database.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int UID;
    public String Name;
    @Nullable
    public String Gender;
    public String Email;
    public String Password;
    @Nullable
    public Integer Age;
    @Nullable
    public String Address;
    @Nullable
    public String ImgSrc;
    @Nullable
    public Float Weight;
    @Nullable
    public Float Height;
    @Nullable
    public String Purpose;
    @Nullable
    public String Token;

}
