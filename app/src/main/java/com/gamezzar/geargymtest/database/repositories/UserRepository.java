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
        AppDatabase db = DatabaseClient.getInstance(application);
        userDao = db.userDao();
    }

    public LiveData<User> findByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    public void insert(User user) {
        executorService.execute(() -> userDao.insert(user));
    }
}


