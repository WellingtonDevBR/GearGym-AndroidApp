package com.gamezzar.geargymtest.database.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gamezzar.geargymtest.database.entities.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    LiveData<List<User>> getAllUsers();

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM User WHERE email Like :email AND password like :password LIMIT 1")
    LiveData<User> findUserByEmailAndPassword(String email, String password);
}
