package com.gamezzar.geargymtest.database.interfaces;

import androidx.room.Dao;
import androidx.room.Insert;

import com.gamezzar.geargymtest.database.entities.Tag;

@Dao
public interface TagDao {
    @Insert
    void insert(Tag tag);
}