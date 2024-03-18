package com.gamezzar.geargymtest.database.repositories;

import android.app.Application;

import androidx.room.Room;

import com.gamezzar.geargymtest.database.AppDatabase;

public class DatabaseClient {
    private static AppDatabase instance;

    private DatabaseClient() {}

    public static synchronized AppDatabase getInstance(Application application) {
        if (instance == null) {
            instance = Room.databaseBuilder(application, AppDatabase.class, "GearGymDB").fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}
