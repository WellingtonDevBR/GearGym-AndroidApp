package com.gamezzar.geargymtest.database.repositories;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.gamezzar.geargymtest.database.AppDatabase;
import com.gamezzar.geargymtest.database.entities.Routine;
import com.gamezzar.geargymtest.database.entities.RoutineWorkout;
import com.gamezzar.geargymtest.database.entities.Set;
import com.gamezzar.geargymtest.database.interfaces.RoutineDao;
import com.gamezzar.geargymtest.database.interfaces.RoutineWorkoutDao;
import com.gamezzar.geargymtest.database.interfaces.SetDao;
import com.gamezzar.geargymtest.database.interfaces.WorkoutDao;
import com.gamezzar.geargymtest.database.models.RoutineWithWorkoutsAndSets;
import com.gamezzar.geargymtest.database.models.WorkoutWithSets;

import java.util.List;

public class RoutineRepository {

    private final RoutineDao routineDao;
    private final RoutineWorkoutDao routineWorkoutDao;
    private final SetDao setDao;
    private final WorkoutDao workoutDao;

    public RoutineRepository(Application application) {
        AppDatabase db = DatabaseClient.getInstance(application);
        routineDao = db.routineDao();
        routineWorkoutDao = db.routineWorkoutDao();
        workoutDao = db.workoutDao();
        setDao = db.setDao();
    }

    public LiveData<List<RoutineWithWorkoutsAndSets>> getAllRoutinesWithWorkoutsAndSets() {
        return routineDao.getAllRoutineWithWorkoutsAndSets();
    }

    public void insertRoutineWithWorkoutsAndSets(Routine routine, List<WorkoutWithSets> workoutsWithSets) {
        new Thread(() -> {
            // Insert the routine and get its ID
            long routineId = routineDao.insert(routine);

            for (WorkoutWithSets workoutWithSets : workoutsWithSets) {
                long workoutId = workoutDao.insert(workoutWithSets.workout);
                RoutineWorkout routineWorkout = new RoutineWorkout();
                routineWorkout.RoutineId = (int) routineId;
                routineWorkout.WorkoutId = (int) workoutId;
                long routineWorkoutId = routineWorkoutDao.insert(routineWorkout);

                for (Set set : workoutWithSets.sets) {
                    set.RoutineWorkoutId = (int) routineWorkoutId;
                    setDao.insert(set);
                }
            }
        }).start();
    }

    public interface DeletionCallback {
        void onDeletionComplete(boolean success);
    }

    public void deleteRoutineWithWorkoutsAndSets(int routineId, DeletionCallback callback) {
        new Thread(() -> {
            // Delete sets linked to this routine through its workouts
            List<RoutineWorkout> routineWorkouts = routineWorkoutDao.findRoutineWorkoutsByRoutineId(routineId);
            for (RoutineWorkout routineWorkout : routineWorkouts) {
                setDao.deleteSetsByRoutineWorkoutId(routineWorkout.UID);
            }
            // Delete the routine workouts linking table entries
            routineWorkoutDao.deleteByRoutineId(routineId);
            // Finally, delete the routine itself
            routineDao.deleteById(routineId);
            new Handler(Looper.getMainLooper()).post(() -> callback.onDeletionComplete(true));
        }).start();
    }
}
