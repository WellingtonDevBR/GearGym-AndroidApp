package com.gamezzar.geargymtest.feature.workout;

public class Workout {

    private String title;
    private String subtitle;
    private int imageResourceId;
    private int workoutCount;

    public Workout(String title, String subtitle, int imageResourceId, int workoutCount) {
        this.title = title;
        this.subtitle = subtitle;
        this.imageResourceId = imageResourceId;
        this.workoutCount = workoutCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public int getCount() {
        return workoutCount;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }

    public void setImageResourceId(int imageResourceId) {
        this.imageResourceId = imageResourceId;
    }

    public int getWorkoutCount() {
        return workoutCount;
    }

    public void setWorkoutCount(int workoutCount) {
        this.workoutCount = workoutCount;
    }

    // Format workout count to display as text
    public String getFormattedWorkoutCount() {
        return workoutCount + " workouts";
    }
}
