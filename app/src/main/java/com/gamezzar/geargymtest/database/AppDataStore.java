package com.gamezzar.geargymtest.database;

import android.content.Context;

import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder;
import androidx.datastore.rxjava3.RxDataStore;

public class AppDataStore {
    private static volatile AppDataStore INSTANCE;
    private final RxDataStore<Preferences> dataStore;

    private AppDataStore(Context context) {
        dataStore = new RxPreferenceDataStoreBuilder(context, "settings").build();
    }

    public static AppDataStore getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDataStore.class) {
                if (INSTANCE == null) {
                    INSTANCE = new AppDataStore(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    // Getter for RxDataStore
    public RxDataStore<Preferences> getDataStore() {
        return dataStore;
    }
}
