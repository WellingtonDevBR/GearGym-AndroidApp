package com.gamezzar.geargymtest.database.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int UID;

    @ColumnInfo(name = "Name")
    public String Name;

    @Nullable
    @ColumnInfo(name = "Gender")
    public String Gender;

    @ColumnInfo(name = "Email")
    public String Email;

    @ColumnInfo(name = "Password")
    public String Password;

    @Nullable
    @ColumnInfo(name = "Age")
    public Integer Age;

    @Nullable
    @ColumnInfo(name = "Address")
    public String Address;

    @Nullable
    @ColumnInfo(name = "ImgSrc")
    public String ImgSrc;

    @Nullable
    @ColumnInfo(name = "Weight")
    public Float Weight;

    @Nullable
    @ColumnInfo(name = "Height")
    public Float Height;

    @Nullable
    @ColumnInfo(name = "Purpose")
    public String Purpose;

    @Nullable
    @ColumnInfo(name = "Token")
    public String Token;

}
