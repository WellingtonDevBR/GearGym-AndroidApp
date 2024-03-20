package com.gamezzar.geargymtest.database.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;

import com.gamezzar.geargymtest.database.entities.EquipmentBodyPart;

@Dao
public interface EquipmentBodyPartDao {
    @Insert
    void insert(EquipmentBodyPart equipmentBodyPart);
}
