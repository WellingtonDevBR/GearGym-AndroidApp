package com.gamezzar.geargymtest.database.repositories;

import android.app.Application;

import com.gamezzar.geargymtest.database.AppDatabase;
import com.gamezzar.geargymtest.database.interfaces.TagDao;

public class TagRepository {

    private final TagDao tagDao;
    public TagRepository(Application application) {
        AppDatabase db = DatabaseClient.getInstance(application);
        tagDao = db.tagDao();
    }
}
