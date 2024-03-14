package com.gamezzar.geargymtest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.database.repositories.UserRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private final UserRepository mRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        mRepository = new UserRepository(application);
    }

    public LiveData<User> login(String email, String password) {
        return mRepository.findByEmailAndPassword(email, password);
    }

    public void register(String name, String email, String password, int age) {
        User user = new User();
        user.Name = name;
        user.Email = email;
        user.Password = password;
        user.Age = age;
        mRepository.insert(user);
    }
}