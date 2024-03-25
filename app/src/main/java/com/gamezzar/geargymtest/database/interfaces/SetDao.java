package com.gamezzar.geargymtest.database.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;

import com.gamezzar.geargymtest.database.entities.Set;

@Dao
public interface SetDao {
    @Insert
    long insert(Set set);
}