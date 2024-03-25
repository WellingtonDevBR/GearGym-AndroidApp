package com.gamezzar.geargymtest.database.entities;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int UID;
    private String Name;
    @Nullable
    private String Gender;
    private String Email;
    private String Password;
    @Nullable
    private Integer Age;
    @Nullable
    private String Address;
    @Nullable
    private String ImgSrc;
    @Nullable
    private Float Weight;
    @Nullable
    private Float Height;
    @Nullable
    private String Purpose;
    @Nullable
    private String Token;

    // Getters
    public int getUID() { return UID; }
    public String getName() { return Name; }
    @Nullable
    public String getGender() { return Gender; }
    public String getEmail() { return Email; }
    public String getPassword() { return Password; }
    @Nullable
    public Integer getAge() { return Age; }
    @Nullable
    public String getAddress() { return Address; }
    @Nullable
    public String getImgSrc() { return ImgSrc; }
    @Nullable
    public Float getWeight() { return Weight; }
    @Nullable
    public Float getHeight() { return Height; }
    @Nullable
    public String getPurpose() { return Purpose; }
    @Nullable
    public String getToken() { return Token; }

    // Setters
    public void setUID(int UID) { this.UID = UID; }
    public void setName(String name) { Name = name; }
    public void setGender(@Nullable String gender) { Gender = gender; }
    public void setEmail(String email) { Email = email; }
    public void setPassword(String password) { Password = password; }
    public void setAge(@Nullable Integer age) { Age = age; }
    public void setAddress(@Nullable String address) { Address = address; }
    public void setImgSrc(@Nullable String imgSrc) { ImgSrc = imgSrc; }
    public void setWeight(@Nullable Float weight) { Weight = weight; }
    public void setHeight(@Nullable Float height) { Height = height; }
    public void setPurpose(@Nullable String purpose) { Purpose = purpose; }
    public void setToken(@Nullable String token) { Token = token; }
}
