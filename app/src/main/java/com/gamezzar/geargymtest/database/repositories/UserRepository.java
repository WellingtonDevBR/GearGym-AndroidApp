package com.gamezzar.geargymtest.database.repositories;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import com.gamezzar.geargymtest.database.AppDatabase;
import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.database.interfaces.UserDao;

import java.util.List;

public class UserRepository {
    private final UserDao userDao;
    private final LiveData<List<User>> allUsers;

    public UserRepository(Application application) {
        AppDatabase db = Room.databaseBuilder(application, AppDatabase.class, "database-name").build();
        userDao = db.userDao();
        allUsers = userDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    public void insert(User user) {
        new insertAsyncTask(userDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void> {
        private final UserDao asyncTaskDao;

        insertAsyncTask(UserDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... params) {
            asyncTaskDao.insertUser(params[0]);
            return null;
        }
    }
}

