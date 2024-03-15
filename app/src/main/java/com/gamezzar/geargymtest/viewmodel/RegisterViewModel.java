package com.gamezzar.geargymtest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.database.repositories.UserRepository;

public class RegisterViewModel extends AndroidViewModel {

    UserRepository userRepository;
    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(getApplication());
    }

    public void signUp(User user) {
        userRepository.insert(user);
    }
}