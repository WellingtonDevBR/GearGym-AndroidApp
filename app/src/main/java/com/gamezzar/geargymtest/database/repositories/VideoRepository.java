package com.gamezzar.geargymtest.database.repositories;

import android.app.Application;

import com.gamezzar.geargymtest.database.AppDatabase;
import com.gamezzar.geargymtest.database.interfaces.VideoDao;

public class VideoRepository {

    private final VideoDao videoDao;
    public VideoRepository(Application application) {
        AppDatabase db = DatabaseClient.getInstance(application);
        videoDao = db.videoDao();
    }
}
