package com.gamezzar.geargymtest.domain;

import java.util.List;

public class RoutineModel {
    private final Integer id;
    private final String title;
    private final String dayOfWeek;
    private List<WorkoutModel> workouts;
    private List<SetModel> sets;

    public RoutineModel(Integer id, String title, String dayOfWeek, List<WorkoutModel> workouts, List<SetModel> sets) {
        this.id = id;
        this.title = title;
        this.dayOfWeek = dayOfWeek;
        this.workouts = workouts;
        this.sets = sets;
    }

    public String getTitle() {
        return title;
    }

    public List<WorkoutModel> getRoutineWorkouts() {
        return workouts;
    }

    public void setRoutineWorkouts(List<WorkoutModel> workouts) {
        this.workouts = workouts;
    }

    public List<SetModel> getWorkoutSets() {
        return sets;
    }

    public void setWorkoutSets(List<SetModel> sets) {
        this.sets = sets;
    }


    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public Integer getId() {
        return id;
    }
}
