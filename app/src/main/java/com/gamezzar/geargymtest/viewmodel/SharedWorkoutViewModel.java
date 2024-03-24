package com.gamezzar.geargymtest.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.gamezzar.geargymtest.domain.WorkoutModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class SharedWorkoutViewModel extends ViewModel {
    private final MutableLiveData<List<WorkoutModel>> selectedWorkouts = new MutableLiveData<>(new ArrayList<>());
    private final MutableLiveData<String> currentBodyPart = new MutableLiveData<>("");

    public void addSelectedWorkout(WorkoutModel workout) {
        List<WorkoutModel> currentWorkoutList = selectedWorkouts.getValue();
        if (currentWorkoutList == null) {
            currentWorkoutList = new ArrayList<>();
        }

        // Check if the list already contains the workout based on some unique identifier, e.g., the title.
        boolean alreadySelected = currentWorkoutList.stream().anyMatch(w -> w.getTitle().equals(workout.getTitle()));

        if (!alreadySelected) {
            currentWorkoutList.add(workout);
            selectedWorkouts.setValue(currentWorkoutList); // Notify observers
        }
    }

    public MutableLiveData<String> getCurrentBodyPart() {
        return this.currentBodyPart;
    }
    public void setCurrentBodyPart(String currentBodyPart) {
        this.currentBodyPart.setValue(currentBodyPart);
    }

    public void removeSelectedWorkout(WorkoutModel workout) {
        List<WorkoutModel> currentSelectedWorkoutList = selectedWorkouts.getValue();
        if (currentSelectedWorkoutList != null && currentSelectedWorkoutList.removeIf(w -> w.getTitle().equals(workout.getTitle()))) {
            selectedWorkouts.setValue(new ArrayList<>(currentSelectedWorkoutList)); // any observable gets notified about the changes
        } else {
            Log.d("SharedWorkoutViewModel", "Workout not found for removal.");
        }
    }

    public LiveData<Integer> getWorkoutCounter() {
        return Transformations.map(selectedWorkouts, List::size);
    }

    public LiveData<List<WorkoutModel>> getSelectedWorkouts() {
        return selectedWorkouts;
    }
}
