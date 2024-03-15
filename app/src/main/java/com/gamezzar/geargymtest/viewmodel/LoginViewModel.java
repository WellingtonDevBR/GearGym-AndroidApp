package com.gamezzar.geargymtest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.database.repositories.UserPreferencesRepository;
import com.gamezzar.geargymtest.database.repositories.UserRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LoginViewModel extends AndroidViewModel {

    private final UserRepository userRepository;
    private final UserPreferencesRepository userPreferencesRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        userPreferencesRepository = new UserPreferencesRepository(application);
    }

    public LiveData<User> login(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }

    public Completable saveEmailToDataStore(String email) {
        return userPreferencesRepository.saveEmail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<String> retrieveEmailFromDataStore() {
        return userPreferencesRepository.getEmail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Completable removeEmailFromDataStore() {
        return userPreferencesRepository.removeEmail()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    protected void onCleared() {
        disposables.clear();
    }
}