package com.gamezzar.geargymtest.database.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.gamezzar.geargymtest.database.AppDatabase;
import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.database.interfaces.UserDao;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {
    private final UserDao userDao;
    private final ExecutorService executorService = Executors.newFixedThreadPool(2); // Using a fixed thread pool

    public UserRepository(Application application) {
        AppDatabase db = Room.databaseBuilder(application,
                AppDatabase.class, "geargym_database").fallbackToDestructiveMigration().build();
        userDao = db.userDao();
    }

    public LiveData<User> findByEmailAndPassword(String email, String password) {
        return userDao.findUserByEmailAndPassword(email, password);
    }

    public void insert(User user) {
        executorService.execute(() -> userDao.insertUser(user));
    }

}


