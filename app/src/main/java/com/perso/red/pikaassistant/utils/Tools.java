package com.perso.red.pikaassistant.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;

import com.perso.red.pikaassistant.R;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by pierr on 18/08/2016.
 */

public class Tools {

    public static boolean isMyServiceRunning(Activity activity, Class<?> serviceClass) {
        ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }

    public static String loadJSONFromAsset(AssetManager assetManager, String file) {
        String json = null;
        try {
            InputStream is = assetManager.open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

    public static Drawable getAssetImage(Context context, String filename) throws IOException {
        AssetManager    assets = context.getResources().getAssets();
        InputStream     buffer = new BufferedInputStream((assets.open(filename)));
        Bitmap          bitmap = BitmapFactory.decodeStream(buffer);

        return new BitmapDrawable(context.getResources(), bitmap);
    }

    public static int convertLevelToIndex(double pokemonLevel){
        return (int)(pokemonLevel * 2 - 2);
    }

    public static double roundEstimatedLvl(double estimatedPokemonLvl){
        return (double)Tools.convertLevelToIndex(estimatedPokemonLvl) / 2 + 1;
    }

    public static String getTrainerLevel(Context context) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);

        return preferences.getString(context.getString(R.string.sp_key), context.getString(R.string.sp_default_value));
    }

    public static void saveTrainerLevel(Context context, String trainerLvl) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString(context.getString(R.string.sp_key), trainerLvl);
        editor.commit();
    }


}
