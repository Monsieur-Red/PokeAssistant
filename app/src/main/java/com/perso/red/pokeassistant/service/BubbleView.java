package com.perso.red.pokeassistant.service;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.perso.red.pokeassistant.R;

import java.util.Calendar;

/**
 * Created by pierr on 19/08/2016.
 */

public class BubbleView extends ImageView implements View.OnTouchListener {

    private static final int MAX_CLICK_DURATION = 200;

    private WindowManager   windowManager;
    private MainUiView      mainUiView;

    private WindowManager.LayoutParams  params;

    private long            startClickTime;
    private int             initialX;
    private int             initialY;
    private float           initialTouchX;
    private float           initialTouchY;

    public BubbleView(Context context) {
        super(context);
    }

    public BubbleView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BubbleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void init(WindowManager windowManager, MainUiView mainUiView) {
        this.windowManager = windowManager;
        this.mainUiView = mainUiView;

        setImageResource(R.drawable.icon);
        setOnTouchListener(this);

        params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE|WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL|WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH,
                PixelFormat.TRANSLUCENT);

        params.gravity = Gravity.TOP | Gravity.LEFT;
        params.x = 0;
        params.y = 100;

        windowManager.addView(this, params);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startClickTime = Calendar.getInstance().getTimeInMillis();
                initialX = params.x;
                initialY = params.y;
                initialTouchX = event.getRawX();
                initialTouchY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                long clickDuration = Calendar.getInstance().getTimeInMillis() - startClickTime;
                if(clickDuration < MAX_CLICK_DURATION) {
                    if (mainUiView.isShown())
                        mainUiView.setVisibility(View.GONE);
                    else
                        mainUiView.setVisibility(View.VISIBLE);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                params.x = initialX + (int) (event.getRawX() - initialTouchX);
                params.y = initialY + (int) (event.getRawY() - initialTouchY);
                windowManager.updateViewLayout(this, params);
                break;
        }
        return true;
    }

}
