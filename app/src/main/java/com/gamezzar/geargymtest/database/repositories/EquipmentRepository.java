package com.gamezzar.geargymtest.database.repositories;

import android.app.Application;

import com.gamezzar.geargymtest.database.AppDatabase;
import com.gamezzar.geargymtest.database.interfaces.EquipmentDao;

public class EquipmentRepository {

    private final EquipmentDao equipmentDao;

    public EquipmentRepository(Application application) {
        AppDatabase db = DatabaseClient.getInstance(application);
        equipmentDao = db.equipmentDao();
    }
}
