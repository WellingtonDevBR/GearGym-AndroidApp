package com.gamezzar.geargymtest.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gamezzar.geargymtest.database.entities.BodyPart;
import com.gamezzar.geargymtest.database.entities.Equipment;
import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.database.entities.Video;
import com.gamezzar.geargymtest.database.entities.Workout;
import com.gamezzar.geargymtest.database.interfaces.BodyPartDao;
import com.gamezzar.geargymtest.database.interfaces.EquipmentDao;
import com.gamezzar.geargymtest.database.interfaces.TagDao;
import com.gamezzar.geargymtest.database.interfaces.UserDao;
import com.gamezzar.geargymtest.database.interfaces.VideoDao;
import com.gamezzar.geargymtest.database.interfaces.WorkoutDao;

@Database(entities = {User.class, Workout.class, Video.class, BodyPart.class, Equipment.class, TagDao.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
    public abstract WorkoutDao workoutDao();
    public abstract VideoDao videoDao();
    public abstract TagDao tagDao();
    public abstract BodyPartDao bodyPartDao();
    public abstract EquipmentDao equipmentDao();
}
