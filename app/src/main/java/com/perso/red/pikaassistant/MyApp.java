package com.perso.red.pikaassistant;

import android.app.Application;

import com.google.android.gms.ads.MobileAds;
import com.squareup.leakcanary.LeakCanary;
import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;

/**
 * Created by pierr on 23/09/2016.
 */

public class MyApp extends Application {

    @Override public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        MobileAds.initialize(getApplicationContext(), getString(R.string.ad_app_id));


        /*
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
*/
    }
}
