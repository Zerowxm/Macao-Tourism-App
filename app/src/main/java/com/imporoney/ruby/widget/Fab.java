package com.imporoney.ruby.widget;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;

import com.gordonwong.materialsheetfab.AnimatedFab;

public class Fab extends FloatingActionButton implements AnimatedFab {

 public Fab(Context context) {
  super(context);
 }

 public Fab(Context context, AttributeSet attrs) {
  super(context, attrs);
 }

 public Fab(Context context, AttributeSet attrs, int defStyleAttr) {
  super(context, attrs, defStyleAttr);
 }

 /**
    * Shows the FAB.
    */
    @Override
    public void show() {
        Log.d("Fab","show");
        // TODO: Animate the FAB into view or simply set its visibility
    }

    /**
     * Shows the FAB and sets the FAB's translation.
     *
     * @param translationX translation X value
     * @param translationY translation Y value
     */
    @Override
    public void show(float translationX, float translationY) {
        // TODO: This is only needed if you want to support moving
        // the FAB around the screen.
        Log.d("Fab","show "+"x:"+translationX+"y:"+translationY);
    }

    /**
     * Hides the FAB.
     */
    @Override
    public void hide() {
        Log.d("Fab","hide");
        // TODO: Animate the FAB out of view or simply set its visibility
    }
}