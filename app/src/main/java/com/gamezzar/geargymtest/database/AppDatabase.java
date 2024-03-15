package com.gamezzar.geargymtest.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.database.interfaces.UserDao;
@Database(entities = {User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
