package com.perso.red.pokeassistant.ivcalculator.view;

import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.utils.Tools;

/**
 * Created by pierr on 25/08/2016.
 */

public class MyArcPointer {

    private WindowManager   windowManager;
    private MyArc           myArc;

    private ImageView       arcPointer;
    private DisplayMetrics  displayMetrics;

    private final WindowManager.LayoutParams arcParams = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.TYPE_PHONE,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            PixelFormat.TRANSLUCENT);

    private int pointerHeight = 0;
    private int pointerWidth = 0;
    private int statusBarHeight = 0;

    public MyArcPointer(Context context, WindowManager windowManager, MyArc myArc) {
        this.windowManager = windowManager;
        this.myArc = myArc;
        createArcPointer(context);
    }

    private void createArcPointer(Context context) {
        displayMetrics = context.getResources().getDisplayMetrics();

        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);

        arcParams.gravity = Gravity.TOP | Gravity.START;
        arcPointer = new ImageView(context);
        arcPointer.setImageResource(R.drawable.arc_pointer);
        arcPointer.setVisibility(View.GONE);

        pointerHeight = ContextCompat.getDrawable(context, R.drawable.arc_pointer).getIntrinsicHeight() / 2;
        pointerWidth = ContextCompat.getDrawable(context, R.drawable.arc_pointer).getIntrinsicWidth() / 2;

        windowManager.addView(arcPointer, arcParams);
    }

    public void setArcPointer(double pokeLevel) {
        int index = Tools.convertLevelToIndex(pokeLevel);

        arcParams.x = myArc.getArcX()[index] - pointerWidth;
        arcParams.y = myArc.getArcY()[index] - pointerHeight - statusBarHeight;

        windowManager.updateViewLayout(arcPointer, arcParams);
    }

    public ImageView getPointerView() {
        return arcPointer;
    }
}
