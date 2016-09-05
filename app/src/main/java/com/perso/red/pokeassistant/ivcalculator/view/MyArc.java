package com.perso.red.pokeassistant.ivcalculator.view;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.perso.red.pokeassistant.utils.Constants;

/**
 * Created by pierr on 25/08/2016.
 */

public class MyArc {

    private MyArcPointer    myArcPointer;

    private int[] arcX;
    private int[] arcY;

    private Point   arcInit;
    private int     arcRadius;

    public MyArc(Context context, WindowManager windowManager) {
        myArcPointer = new MyArcPointer(context, windowManager, this);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();

        arcInit = new Point();

        arcInit.x = (int) ((displayMetrics.widthPixels * 0.5));
        arcInit.y = (int) Math.floor(displayMetrics.heightPixels / 2.803943);
        if (displayMetrics.heightPixels == 2392 || displayMetrics.heightPixels == 800)
            arcInit.y--;
        else if (displayMetrics.heightPixels == 1920)
            arcInit.y++;

        arcRadius = (int) Math.round(displayMetrics.heightPixels / 4.3760683);
        if (displayMetrics.heightPixels == 1776 || displayMetrics.heightPixels == 960 || displayMetrics.heightPixels == 800)
            arcRadius++;

        setupArcPoints(1);
        myArcPointer.setArcPointer(1);
    }

    public void setupArcPoints(int trainerLevel) {
        final int maxPokeLevelIdx = Math.min(2 * trainerLevel + 1, 78);
        arcX = new int[maxPokeLevelIdx + 1];
        arcY = new int[maxPokeLevelIdx + 1];

        double baseCpM = Constants.CpM[0];
        double maxPokeCpMDelta = Constants.CpM[Math.min(maxPokeLevelIdx + 1, Constants.CpM.length - 1)] - baseCpM;

        //pokeLevelIdx <= maxPokeLevelIdx ensures we never overflow CpM/arc/arcY.
        for (int pokeLevelIdx = 0; pokeLevelIdx <= maxPokeLevelIdx; pokeLevelIdx++) {
            double pokeCurrCpMDelta = Constants.CpM[pokeLevelIdx] - baseCpM;
            double arcRatio = pokeCurrCpMDelta / maxPokeCpMDelta;
            double angleInRadians = (arcRatio + 1) * Math.PI;

            arcX[pokeLevelIdx] = (int) (arcInit.x + (arcRadius * Math.cos(angleInRadians)));
            arcY[pokeLevelIdx] = (int) (arcInit.y + (arcRadius * Math.sin(angleInRadians)));
        }
    }

    public MyArcPointer getMyArcPointer() {
        return myArcPointer;
    }

    public int[] getArcX() {
        return arcX;
    }

    public int[] getArcY() {
        return arcY;
    }
}
