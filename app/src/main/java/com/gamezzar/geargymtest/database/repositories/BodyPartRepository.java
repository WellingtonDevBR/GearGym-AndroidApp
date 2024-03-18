package com.gamezzar.geargymtest.database.repositories;

import android.app.Application;

import com.gamezzar.geargymtest.database.AppDatabase;
import com.gamezzar.geargymtest.database.entities.BodyPart;
import com.gamezzar.geargymtest.database.interfaces.BodyPartDao;

public class BodyPartRepository {

    private final BodyPartDao bodyPartDao;

    public BodyPartRepository(Application application) {
        AppDatabase db = DatabaseClient.getInstance(application);
        bodyPartDao = db.bodyPartDao();
    }
}
