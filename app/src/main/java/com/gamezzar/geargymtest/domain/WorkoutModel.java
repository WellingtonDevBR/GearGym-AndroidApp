package com.gamezzar.geargymtest.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WorkoutModel implements Parcelable {

    private final String title;
    private final String duration;
    private String imageUrl;
    private Boolean isChecked;
    private List<SetModel> sets = new ArrayList<>();


    public WorkoutModel(String title, String duration, String imageUrl) {
        this.title = title;
        this.duration = duration;
        this.imageUrl = imageUrl;
        isChecked = false;
    }

    public WorkoutModel(Parcel in) {
        title = in.readString();
        duration = in.readString();
        imageUrl = in.readString();
        isChecked = in.readByte() != 0;
        sets = in.createTypedArrayList(SetModel.CREATOR);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, duration, imageUrl);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(duration);
        dest.writeString(imageUrl);
        dest.writeByte((byte) (isChecked ? 1 : 0));
        dest.writeTypedList(sets);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WorkoutModel> CREATOR = new Creator<WorkoutModel>() {
        @Override
        public WorkoutModel createFromParcel(Parcel in) {
            return new WorkoutModel(in);
        }

        @Override
        public WorkoutModel[] newArray(int size) {
            return new WorkoutModel[size];
        }
    };

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getWorkoutDuration() {
        return duration;
    }

    public Boolean getChecked() {
        return isChecked;
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public List<SetModel> getSets() {
        return sets;
    }

    public interface SetsChangeListener {
        void onSetsChanged();
    }

    public void addSet(SetModel sets) {
        this.sets.add(sets);
    }

    private transient SetsChangeListener setsChangeListener; // transient for Parcelable


    public void deleteSetAtPosition(int position) {
        if (position >= 0 && position < sets.size()) {
            sets.remove(position);
            if (setsChangeListener != null) setsChangeListener.onSetsChanged();
        }
    }


}
