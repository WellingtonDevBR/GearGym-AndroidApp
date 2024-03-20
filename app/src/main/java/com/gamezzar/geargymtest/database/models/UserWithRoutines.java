package com.gamezzar.geargymtest.database.models;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.gamezzar.geargymtest.database.entities.User;

import java.util.List;

public class UserWithRoutines {
    @Embedded
    public User user;
    @Relation(parentColumn = "UID", entityColumn = "UserId")
    public List<RoutineWithWorkoutsAndSets> routines;
}