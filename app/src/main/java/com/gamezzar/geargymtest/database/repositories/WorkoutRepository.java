package com.gamezzar.geargymtest.database.repositories;

import android.app.Application;

import androidx.room.Room;

import com.gamezzar.geargymtest.database.AppDatabase;
import com.gamezzar.geargymtest.database.interfaces.WorkoutDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WorkoutRepository {

    private final WorkoutDao workoutDao;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public WorkoutRepository(Application application) {
        AppDatabase db = DatabaseClient.getInstance(application);
        workoutDao = db.workoutDao();
    }
}
