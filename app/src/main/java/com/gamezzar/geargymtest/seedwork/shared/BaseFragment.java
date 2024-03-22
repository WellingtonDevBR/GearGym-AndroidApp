package com.gamezzar.geargymtest.seedwork.shared;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.gamezzar.geargymtest.R;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BaseFragment extends Fragment {
    public void showToolBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().show();
        }
    }

    public void hideToolBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null && activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().hide();
        }
    }

    public void showBottomAppBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            BottomAppBar bottomAppBar = activity.findViewById(R.id.bottom_app_bar);
            FloatingActionButton floatingActionButton = activity.findViewById(R.id.fab);
            if (bottomAppBar != null) {
                bottomAppBar.setVisibility(View.VISIBLE);
                floatingActionButton.setVisibility(View.VISIBLE);
            }
        }
    }

    public void hideBottomAppBar() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if (activity != null) {
            BottomAppBar bottomAppBar = activity.findViewById(R.id.bottom_app_bar);
            FloatingActionButton floatingActionButton = activity.findViewById(R.id.fab);
            if (bottomAppBar != null) {
                bottomAppBar.setVisibility(View.INVISIBLE);
                floatingActionButton.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void showFab() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        FloatingActionButton floatingActionButton = activity.findViewById(R.id.fab);
        assert floatingActionButton != null;
        floatingActionButton.setVisibility(View.VISIBLE);
    }

    public void hideFab() {
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        assert activity != null;
        FloatingActionButton floatingActionButton = activity.findViewById(R.id.fab);
        assert floatingActionButton != null;
        floatingActionButton.setVisibility(View.GONE);
    }
}