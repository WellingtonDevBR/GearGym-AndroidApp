package com.gamezzar.geargymtest.database.repositories;

import android.app.Application;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import androidx.datastore.preferences.core.PreferencesKeys;
import androidx.datastore.rxjava3.RxDataStore;

import com.gamezzar.geargymtest.database.AppDataStore;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;

public class UserPreferencesRepository {

    private final RxDataStore<Preferences> dataStore;

    public UserPreferencesRepository(Application application) {
        dataStore = AppDataStore.getInstance(application.getApplicationContext()).getDataStore();
    }

    public Flowable<String> getEmail() {
        Preferences.Key<String> emailKey = PreferencesKeys.stringKey("user_email");
        return dataStore.data().map(prefs -> prefs.get(emailKey));
    }

    public Completable saveEmail(String email) {
        Preferences.Key<String> emailKey = PreferencesKeys.stringKey("user_email");
        return dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            mutablePreferences.set(emailKey, email);
            return Single.just(mutablePreferences);
        }).ignoreElement();
    }

    public Completable removeEmail() {
        Preferences.Key<String> emailKey = PreferencesKeys.stringKey("user_email");
        return dataStore.updateDataAsync(prefsIn -> {
            MutablePreferences mutablePreferences = prefsIn.toMutablePreferences();
            mutablePreferences.remove(emailKey);
            return Single.just(mutablePreferences);
        }).ignoreElement(); // Convert to Completable
    }
}
