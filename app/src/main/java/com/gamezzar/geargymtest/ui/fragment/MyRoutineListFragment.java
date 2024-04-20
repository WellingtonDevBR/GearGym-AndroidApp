package com.gamezzar.geargymtest.ui.fragment;

import static com.google.android.material.color.MaterialColors.ALPHA_FULL;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.amazonaws.regions.Regions;
import com.gamezzar.geargymtest.BuildConfig;
import com.gamezzar.geargymtest.R;
import com.gamezzar.geargymtest.database.entities.Set;
import com.gamezzar.geargymtest.database.models.RoutineWithWorkoutsAndSets;
import com.gamezzar.geargymtest.database.models.WorkoutWithSets;
import com.gamezzar.geargymtest.databinding.MyRoutineListFragmentBinding;
import com.gamezzar.geargymtest.domain.RoutineModel;
import com.gamezzar.geargymtest.domain.SetModel;
import com.gamezzar.geargymtest.seedwork.service.AWSS3Service;
import com.gamezzar.geargymtest.seedwork.shared.BaseFragment;
import com.gamezzar.geargymtest.domain.WorkoutModel;
import com.gamezzar.geargymtest.ui.adapter.MyRoutineListAdapter;
import com.gamezzar.geargymtest.viewmodel.MyRoutineListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MyRoutineListFragment extends BaseFragment {

    private MyRoutineListViewModel mViewModel;
    private MyRoutineListFragmentBinding binding;
    private MyRoutineListAdapter adapter;
    private List<WorkoutModel> workoutList;
    private AWSS3Service awss3Service;
    private List<RoutineModel> routineModels;

    public static MyRoutineListFragment newInstance() {
        return new MyRoutineListFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = MyRoutineListFragmentBinding.inflate(inflater, container, false);
        awss3Service = new AWSS3Service(requireContext(), BuildConfig.AWS_IDENTITY_POOL_ID, Regions.US_EAST_2);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MyRoutineListViewModel.class);
        mViewModel.getWorkoutsSet().observe(getViewLifecycleOwner(), routinesWithWorkoutsAndSets -> {
            if (!routinesWithWorkoutsAndSets.isEmpty()) {
                routineModels = new ArrayList<>();
                for (RoutineWithWorkoutsAndSets rWithWorkoutsAndSets : routinesWithWorkoutsAndSets) {
                    List<WorkoutModel> workoutModels = convertToWorkoutModels(rWithWorkoutsAndSets.workouts);
                    List<SetModel> setModels = convertToSetModels(rWithWorkoutsAndSets.workouts);
                    RoutineModel routineModel = new RoutineModel(rWithWorkoutsAndSets.routine.UID, rWithWorkoutsAndSets.routine.Name, rWithWorkoutsAndSets.routine.DayOfWeek, workoutModels, setModels);
                    routineModels.add(routineModel);
                }
                adapter = new MyRoutineListAdapter(routineModels, routineModel -> {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("selectedRoutine", routineModel);
                    Navigation.findNavController(view).navigate(R.id.action_routineWorkoutFragment_to_routineWorkoutListFragment, bundle);
                });
                binding.routineRecycleView.setLayoutManager(new LinearLayoutManager(getContext()));
                binding.routineRecycleView.setAdapter(adapter);
            }
        });
        ItemTouchHelper itemTouchHelper = getItemTouchHelper();
        itemTouchHelper.attachToRecyclerView(binding.routineRecycleView);

    }

    @NonNull
    private ItemTouchHelper getItemTouchHelper() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // This callback is not used for swipe actions, so return false.
                return false;
            }


            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // Handle the swipe to delete action here.
                int position = viewHolder.getAdapterPosition();
                int routineId = adapter.getItemAtPosition(position).getId();
                mViewModel.deleteRoutine(routineId, success -> {
                    if (success) {
                        requireActivity().runOnUiThread(() -> {
                            routineModels.remove(position);
                            adapter.notifyItemRemoved(position);
                        });
                    } else {
                        Toast.makeText(requireContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }


            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder,
                                    float dX, float dY, int actionState, boolean isCurrentlyActive) {
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE && dX < 0) { // Only consider left swipes.
                    View itemView = viewHolder.itemView;
                    Paint p = new Paint();
                    p.setColor(Color.parseColor("#D32F2F"));

                    // Draw background
                    c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                            (float) itemView.getRight(), (float) itemView.getBottom(), p);

                    // Draw icon
                    Drawable icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_trash_svg_48);
                    if (icon != null) {
                        int itemHeight = itemView.getBottom() - itemView.getTop();
                        int intrinsicWidth = icon.getIntrinsicWidth();
                        int intrinsicHeight = icon.getIntrinsicHeight();

                        int iconLeft = itemView.getRight() - itemView.getPaddingRight() - intrinsicWidth;
                        int iconRight = itemView.getRight() - itemView.getPaddingRight();
                        int iconTop = itemView.getTop() + (itemHeight - intrinsicHeight) / 2;
                        int iconBottom = iconTop + intrinsicHeight;
                        icon.setBounds(iconLeft, iconTop, iconRight, iconBottom);

                        icon.draw(c);
                    }

                    itemView.setAlpha(ALPHA_FULL - Math.abs(dX) / (float) viewHolder.itemView.getWidth());
                    itemView.setTranslationX(dX);
                } else {
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
            }
        };
        return new ItemTouchHelper(simpleItemTouchCallback);
    }



    private List<WorkoutModel> convertToWorkoutModels(List<WorkoutWithSets> workoutsWithSets) {
        List<WorkoutModel> workoutModels = new ArrayList<>();

        for (WorkoutWithSets workoutWithSets : workoutsWithSets) {
            WorkoutModel workoutModel = new WorkoutModel(workoutWithSets.workout.Name, workoutWithSets.workout.Duration, workoutWithSets.workout.ImageUri);
            for (Set set : workoutWithSets.sets) {
                SetModel setModel = new SetModel(String.valueOf(set.UID), set.Weight.toString(), set.Repetition.toString());
                workoutModel.addSet(setModel);
            }

            workoutModels.add(workoutModel);
        }

        return workoutModels;
    }


    private List<SetModel> convertToSetModels(List<WorkoutWithSets> workouts) {
        List<SetModel> models = new ArrayList<>();
        Integer counter = 0;
        for (WorkoutWithSets w : workouts) {
            for (Set s : w.sets) {
                counter++;
                models.add(new SetModel(counter.toString(), s.Weight.toString(), s.Repetition.toString()));
            }
        }
        return models;
    }


    @Override
    public void onResume() {
        super.onResume();
        hideBottomAppBar();
        hideFab();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}