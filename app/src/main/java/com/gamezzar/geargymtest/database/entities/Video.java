package com.gamezzar.geargymtest.database.entities;

import androidx.annotation.Nullable;
import androidx.room.Entity;

@Entity
public class Video {
    public Integer UID;
    public String Uri;
    @Nullable
    public String ThumbNailUri;
    @Nullable
    public Float Duration;
}
