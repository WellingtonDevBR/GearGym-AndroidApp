package com.gamezzar.geargymtest.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gamezzar.geargymtest.database.entities.BodyPart;
import com.gamezzar.geargymtest.database.entities.Equipment;
import com.gamezzar.geargymtest.database.entities.EquipmentBodyPart;
import com.gamezzar.geargymtest.database.entities.Routine;
import com.gamezzar.geargymtest.database.entities.Set;
import com.gamezzar.geargymtest.database.entities.Tag;
import com.gamezzar.geargymtest.database.entities.User;
import com.gamezzar.geargymtest.database.entities.Video;
import com.gamezzar.geargymtest.database.entities.Workout;
import com.gamezzar.geargymtest.database.entities.RoutineWorkout;
import com.gamezzar.geargymtest.database.interfaces.BodyPartDao;
import com.gamezzar.geargymtest.database.interfaces.EquipmentBodyPartDao;
import com.gamezzar.geargymtest.database.interfaces.EquipmentDao;
import com.gamezzar.geargymtest.database.interfaces.RoutineDao;
import com.gamezzar.geargymtest.database.interfaces.SetDao;
import com.gamezzar.geargymtest.database.interfaces.TagDao;
import com.gamezzar.geargymtest.database.interfaces.UserDao;
import com.gamezzar.geargymtest.database.interfaces.VideoDao;
import com.gamezzar.geargymtest.database.interfaces.WorkoutDao;
import com.gamezzar.geargymtest.database.interfaces.RoutineWorkoutDao;

@Database(entities = {User.class, EquipmentBodyPart.class, Workout.class, Video.class, BodyPart.class,
        Equipment.class, Tag.class, RoutineWorkout.class, Routine.class, Set.class}, version = 8)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

    public abstract WorkoutDao workoutDao();

    public abstract VideoDao videoDao();

    public abstract TagDao tagDao();

    public abstract BodyPartDao bodyPartDao();

    public abstract EquipmentDao equipmentDao();

    public abstract RoutineDao routineDao();

    public abstract SetDao setDao();

    public abstract RoutineWorkoutDao routineWorkoutDao();

    public abstract EquipmentBodyPartDao equipmentBodyPartDao();
}
