package com.gamezzar.geargymtest.ui.adapter;

import com.gamezzar.geargymtest.domain.SetModel;
import com.gamezzar.geargymtest.domain.WorkoutModel;

public interface OnSetRemovedListener {
    void onSetRemoved(SetModel set, int position, WorkoutModel workout);
}