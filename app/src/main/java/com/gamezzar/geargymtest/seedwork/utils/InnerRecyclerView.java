package com.gamezzar.geargymtest.seedwork.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import androidx.recyclerview.widget.RecyclerView;

public class InnerRecyclerView extends RecyclerView {
    private GestureDetector gestureDetector;

    public InnerRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public InnerRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public InnerRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                // Check if scrolling more horizontally than vertically
                if (Math.abs(distanceX) > Math.abs(distanceY)) {
                    // Horizontal scroll detected, request parent to intercept
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                return super.onScroll(e1, e2, distanceX, distanceY);
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        gestureDetector.onTouchEvent(e);
        // Initially, don't let parent intercept touch events (for vertical scrolling to work normally)
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(e);
    }
}

