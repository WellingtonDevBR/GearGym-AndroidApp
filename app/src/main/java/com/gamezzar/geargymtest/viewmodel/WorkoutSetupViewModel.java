package com.gamezzar.geargymtest.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.gamezzar.geargymtest.database.entities.Routine;
import com.gamezzar.geargymtest.database.models.WorkoutWithSets;
import com.gamezzar.geargymtest.database.repositories.RoutineRepository;

import java.util.List;


public class WorkoutSetupViewModel extends AndroidViewModel {

    private RoutineRepository routineRepository;

    public WorkoutSetupViewModel(@NonNull Application application) {
        super(application);
        routineRepository = new RoutineRepository(application);
    }

    public void saveRoutineWithWorkoutsAndSets(Routine routine, List<WorkoutWithSets> workoutsWithSets) {
        routineRepository.insertRoutineWithWorkoutsAndSets(routine, workoutsWithSets);
    }
}