package com.fuady.ssa_app_redo1;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by fuady on 9/17/2017.
 */
public class OnSwipeTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;

    public OnSwipeTouchListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    public void onSwipeLeft() {
        System.out.println("left");
        System.out.println("left");
        System.out.println("left");
        System.out.println("left");
    }

    public void onSwipeRight() {
        System.out.println("Right");
        System.out.println("Right");
        System.out.println("Right");
        System.out.println("Right");
    }
    /*public void onLongClick() {
        System.out.println("tRigh");
        System.out.println("Rightasdfa");
        System.out.println("Rdsfasasdfaight");
        System.out.println("Rigadfaht");

    }*/
    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_DISTANCE_THRESHOLD = 150;
        private static final int SWIPE_VELOCITY_THRESHOLD = 150;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (distanceX > 0)
                    onSwipeRight();
                else
                    onSwipeLeft();
                return true;
                //return false;
            }
            return false;
            //return true;
        }
    }
}