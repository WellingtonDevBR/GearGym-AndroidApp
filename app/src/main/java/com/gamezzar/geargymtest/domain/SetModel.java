package com.gamezzar.geargymtest.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class SetModel implements Parcelable {
    private String row;
    private String kg;
    private String repetitions;

    // Corrected constructor (removed extra semicolon)
    public SetModel(String row, String kg, String repetitions) {
        this.row = row;
        this.kg = kg;
        this.repetitions = repetitions;
    }
    // Parcelable constructor
    protected SetModel(Parcel in) {
        row = in.readString();
        kg = in.readString();
        repetitions = in.readString();
    }

    // Parcelable.Creator
    public static final Creator<SetModel> CREATOR = new Creator<SetModel>() {
        @Override
        public SetModel createFromParcel(Parcel in) {
            return new SetModel(in);
        }

        @Override
        public SetModel[] newArray(int size) {
            return new SetModel[size];
        }
    };

    // Getters and Setters
    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }

    public String getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(String repetitions) {
        this.repetitions = repetitions;
    }


    // Parcelable methods
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(row);
        dest.writeString(kg);
        dest.writeString(repetitions);
    }
}
