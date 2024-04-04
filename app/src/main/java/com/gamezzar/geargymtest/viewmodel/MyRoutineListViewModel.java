package com.gamezzar.geargymtest.viewmodel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.gamezzar.geargymtest.database.models.RoutineWithWorkoutsAndSets;
import com.gamezzar.geargymtest.database.repositories.RoutineRepository;
import com.gamezzar.geargymtest.domain.WorkoutModel;

import java.util.List;

public class MyRoutineListViewModel extends AndroidViewModel {
    private RoutineRepository routineRepository;
    private LiveData<List<RoutineWithWorkoutsAndSets>> routinesWithWorkoutsAndSets;

    public MyRoutineListViewModel(@NonNull Application application) {
        super(application);
        routineRepository = new RoutineRepository(application);
        routinesWithWorkoutsAndSets = routineRepository.getAllRoutinesWithWorkoutsAndSets();
    }

    public LiveData<List<RoutineWithWorkoutsAndSets>> getWorkoutsSet() {
        return routinesWithWorkoutsAndSets;
    }

    public void deleteRoutine(int routineId, RoutineRepository.DeletionCallback callback) {
        routineRepository.deleteRoutineWithWorkoutsAndSets(routineId, callback);
    }
}
