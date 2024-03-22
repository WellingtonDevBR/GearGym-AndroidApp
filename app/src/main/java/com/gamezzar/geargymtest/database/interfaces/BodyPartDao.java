package com.gamezzar.geargymtest.database.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.gamezzar.geargymtest.database.entities.BodyPart;
import com.gamezzar.geargymtest.database.models.BodyPartWithWorkouts;

import java.util.List;

@Dao
public interface BodyPartDao {
    @Insert
    void insert(BodyPart bodyPart);

    @Query("SELECT * FROM BodyPart WHERE Name = :name")
    LiveData<BodyPart> findByName(String name);


    @Query("SELECT * FROM BodyPart")
    LiveData<List<BodyPart>> getAll();

    @Transaction
    @Query("SELECT * FROM BodyPart")
    LiveData<List<BodyPartWithWorkouts>> getAllBodyPartsAndWorkouts();

}
