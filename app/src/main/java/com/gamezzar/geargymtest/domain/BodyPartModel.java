package com.gamezzar.geargymtest.domain;

import com.gamezzar.geargymtest.database.entities.Workout;

import java.util.List;

public class BodyPartModel {

    private final String title;
    private final String imageUrl;
    private final int workoutCounter;
    private final List<WorkoutModel> workoutList;

    public BodyPartModel(String title, String imageUrl, int workoutCounter, List<WorkoutModel> workoutList) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.workoutCounter = workoutCounter;
        this.workoutList = workoutList;
    }

    public String getName() {
        return title;
    }

    public String getImageURL() {
        return imageUrl;
    }

    public Integer getWorkoutSize() {
        return workoutCounter;
    }

    public List<WorkoutModel> workoutList() {
        return workoutList;
    }

}
