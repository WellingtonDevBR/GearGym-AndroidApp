package com.gamezzar.geargymtest.database.interfaces;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.gamezzar.geargymtest.database.entities.User;

@Dao
public interface UserDao {
    @Insert
    void insert(User user);

    @Query("SELECT * FROM User WHERE Email = :email AND Password = :password LIMIT 1")
    LiveData<User> findByEmailAndPassword(String email, String password);
}
