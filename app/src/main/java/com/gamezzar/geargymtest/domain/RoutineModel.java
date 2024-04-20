package com.gamezzar.geargymtest.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class RoutineModel implements Parcelable {
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
    protected RoutineModel(Parcel in) {
        // 'readValue' method is used for boxed types like Integer
        id = (Integer) in.readValue(Integer.class.getClassLoader());
        title = in.readString();
        dayOfWeek = in.readString();
        // Make sure WorkoutModel and SetModel are also Parcelable
        workouts = in.createTypedArrayList(WorkoutModel.CREATOR);
        sets = in.createTypedArrayList(SetModel.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id); // Use writeValue for boxed types
        dest.writeString(title);
        dest.writeString(dayOfWeek);
        dest.writeTypedList(workouts);
        dest.writeTypedList(sets);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<RoutineModel> CREATOR = new Parcelable.Creator<RoutineModel>() {
        @Override
        public RoutineModel createFromParcel(Parcel in) {
            return new RoutineModel(in);
        }

        @Override
        public RoutineModel[] newArray(int size) {
            return new RoutineModel[size];
        }
    };

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
