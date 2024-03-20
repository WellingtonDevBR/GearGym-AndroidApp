package com.gamezzar.geargymtest.database.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gamezzar.geargymtest.database.entities.Tag;

import java.util.List;

@Dao
public interface TagDao {
    @Insert
    Long insertTag(Tag tag);

    @Query("SELECT * FROM Tag WHERE EquipmentId = :equipmentId")
    LiveData<List<Tag>> getTagsForEquipment(int equipmentId);

}