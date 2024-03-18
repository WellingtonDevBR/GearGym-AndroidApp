package com.gamezzar.geargymtest.database.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gamezzar.geargymtest.database.entities.BodyPart;

@Dao
public interface BodyPartDao {
    @Insert
    void insert(BodyPart bodyPart);

    @Query("SELECT * FROM BodyPart WHERE Name = :name")
    LiveData<BodyPart> findByName(String name);
}
