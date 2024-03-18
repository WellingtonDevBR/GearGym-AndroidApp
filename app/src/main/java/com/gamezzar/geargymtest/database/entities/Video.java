package com.gamezzar.geargymtest.database.entities;

import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Video {
    @PrimaryKey(autoGenerate = true)
    public Integer UID;
    public String Uri;
    @Nullable
    public String ThumbNailUri;
    @Nullable
    public Float Duration;
}
