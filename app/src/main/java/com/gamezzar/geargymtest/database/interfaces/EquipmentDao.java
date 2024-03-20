package com.gamezzar.geargymtest.database.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.gamezzar.geargymtest.database.entities.Equipment;
import com.gamezzar.geargymtest.database.models.EquipmentWithVideosAndBodyPart;

import java.util.List;

@Dao
public interface EquipmentDao {
    @Insert
    void insert(Equipment equipment);

    @Query("SELECT * FROM Equipment")
    LiveData<List<Equipment>> getAllEquipments();

    @Transaction
    @Query("SELECT * FROM Equipment WHERE UID = :equipmentId")
    LiveData<EquipmentWithVideosAndBodyPart> getEquipmentWithVideosAndBodyParts(int equipmentId);
}
