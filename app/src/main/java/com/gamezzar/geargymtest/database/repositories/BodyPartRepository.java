package com.gamezzar.geargymtest.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.gamezzar.geargymtest.database.AppDatabase;
import com.gamezzar.geargymtest.database.entities.BodyPart;
import com.gamezzar.geargymtest.database.interfaces.BodyPartDao;
import com.gamezzar.geargymtest.database.models.BodyPartWithWorkouts;

import java.util.List;

public class BodyPartRepository {
    private final BodyPartDao bodyPartDao;

    public BodyPartRepository(Application application) {
        AppDatabase db = DatabaseClient.getInstance(application);
        bodyPartDao = db.bodyPartDao();
    }

    public LiveData<List<BodyPart>> get() {
        return bodyPartDao.getAll();
    }

    public LiveData<List<BodyPartWithWorkouts>> getAllBodyPartsWithWorkouts() {
        return bodyPartDao.getAllBodyPartsAndWorkouts();
    }
}
