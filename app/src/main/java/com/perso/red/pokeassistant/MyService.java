package com.perso.red.pokeassistant;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.perso.red.pokeassistant.mainUi.MainUiView;
import com.perso.red.pokeassistant.models.ViewsSelected;
import com.perso.red.pokeassistant.utils.Constants;
import com.perso.red.pokeassistant.utils.Tools;

import jp.co.recruit_lifestyle.android.floatingview.FloatingViewListener;
import jp.co.recruit_lifestyle.android.floatingview.FloatingViewManager;

/**
 * Created by pierr on 18/08/2016.
 */

public class MyService extends Service implements FloatingViewListener {

    private WindowManager       windowManager;
    private MainUiView          mainUiView;
    private FloatingViewManager mFloatingViewManager;

    @Override public IBinder onBind(Intent intent) {
        return null;
    }

    @Override public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mainUiView = new MainUiView(this, windowManager, (ViewsSelected) intent.getExtras().get(Constants.VIEW_SELECTED));

        if (mFloatingViewManager != null)
            return START_STICKY;

        final DisplayMetrics metrics = new DisplayMetrics();
        final WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);
        final ImageView bubbleView = new ImageView(this);
        bubbleView.setImageResource(R.drawable.icon);

        bubbleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainUiView.setVisibility();
            }
        });

        mFloatingViewManager = new FloatingViewManager(this, this);
        mFloatingViewManager.setFixedTrashIconImage(R.drawable.ic_trash_fixed);
        mFloatingViewManager.setActionTrashIconImage(R.drawable.ic_trash_action);
        final FloatingViewManager.Options options = new FloatingViewManager.Options();
        options.shape = FloatingViewManager.SHAPE_CIRCLE;
        mFloatingViewManager.addViewToWindow(bubbleView, options);

        return START_REDELIVER_INTENT;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mainUiView != null)
            mainUiView.removeView(windowManager);
        if (mFloatingViewManager != null) {
            mFloatingViewManager.removeAllViewToWindow();
            mFloatingViewManager = null;
        }
    }

    @Override
    public void onFinishFloatingView() {
        String  trainerLvl = mainUiView.getTrainerLvl();

        if (!TextUtils.isEmpty(trainerLvl))
            Tools.saveTrainerLevel(this, trainerLvl);

        stopSelf();
    }
}
