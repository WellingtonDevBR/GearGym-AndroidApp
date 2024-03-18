package com.gamezzar.geargymtest.database.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;

import com.gamezzar.geargymtest.database.entities.Video;

@Dao
public interface VideoDao {
    @Insert
    void insert(Video video);
}
