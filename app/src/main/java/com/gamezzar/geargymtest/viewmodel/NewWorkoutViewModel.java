package com.gamezzar.geargymtest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gamezzar.geargymtest.database.entities.BodyPart;
import com.gamezzar.geargymtest.database.models.BodyPartWithWorkouts;
import com.gamezzar.geargymtest.database.repositories.BodyPartRepository;

import java.util.List;

public class NewWorkoutViewModel extends AndroidViewModel {

    private BodyPartRepository bodyPartRepository;

    public NewWorkoutViewModel(@NonNull Application application) {
        super(application);
        bodyPartRepository = new BodyPartRepository(application);
    }

    public LiveData<List<BodyPart>> retrieveAllBodyParts() {
        return bodyPartRepository.get();
    }

    public LiveData<List<BodyPartWithWorkouts>> retrieveAllBodyPartsAndWorkouts() {
        return bodyPartRepository.getAllBodyPartsWithWorkouts();
    }

}