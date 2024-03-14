package com.gamezzar.geargymtest.database;

import androidx.room.RoomDatabase;

import com.gamezzar.geargymtest.database.interfaces.UserDao;

public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
