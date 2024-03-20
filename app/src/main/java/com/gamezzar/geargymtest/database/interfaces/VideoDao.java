package com.gamezzar.geargymtest.database.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gamezzar.geargymtest.database.entities.Video;

import java.util.List;

@Dao
public interface VideoDao {
    @Insert
    void insert(Video video);
    @Query("SELECT * FROM Video WHERE EquipmentId = :equipmentId")
    LiveData<List<Video>> getVideosForEquipment(int equipmentId);
}
