package com.perso.red.pokeassistant.service;

import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.view.WindowManager;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.utils.Tools;

/**
 * Created by pierr on 18/08/2016.
 */

public class MyService extends Service {

    private WindowManager   windowManager;
    private BubbleView      bubbleView;
    private MainUiView      mainUiView;

    @Override public IBinder onBind(Intent intent) {
        return null;
    }

    @Override public void onCreate() {
        super.onCreate();

        windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

        mainUiView = new MainUiView(this);
        mainUiView.init(windowManager);

        bubbleView = new BubbleView(this);
        bubbleView.init(windowManager, mainUiView);

        setupServiceNotificationBar();
    }

    protected BroadcastReceiver stopServiceReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String  trainerLvl = mainUiView.getTrainerLvl();

            if (!TextUtils.isEmpty(trainerLvl))
                Tools.saveTrainerLevel(context, trainerLvl);

            unregisterReceiver(stopServiceReceiver);
            stopSelf();
        }
    };

    private void setupServiceNotificationBar(){
        registerReceiver(stopServiceReceiver, new IntentFilter("myFilter"));
        PendingIntent contentIntent = PendingIntent.getBroadcast(this, 0, new Intent("myFilter"), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setAutoCancel(false);
        builder.setContentTitle(getString(R.string.app_name));
        builder.setContentText(getString(R.string.notif_text));
        builder.setSubText(getString(R.string.quit_app));
        builder.setSmallIcon(R.drawable.icon);
        builder.setContentIntent(contentIntent);
        builder.setOngoing(true);
        builder.build();

        startForeground(1, builder.getNotification());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mainUiView != null)
            mainUiView.removeView(windowManager);
        if (bubbleView != null)
            windowManager.removeView(bubbleView);
    }
}
