package com.perso.red.pokeassistant.ivcalculator.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.perso.red.pokeassistant.utils.Constants;
import com.perso.red.pokeassistant.utils.Tools;

/**
 * Created by pierr on 25/08/2016.
 */

public class MyArc {

    private MyArcPointer    myArcPointer;
    private DisplayMetrics  displayMetrics;

    private int[] arcX;
    private int[] arcY;

    private int arcCenter;
    private int arcInitialY;
    private int radius;

    public MyArc(Context context, WindowManager windowManager) {
        myArcPointer = new MyArcPointer(context, windowManager, this);
        displayMetrics = context.getResources().getDisplayMetrics();

        // TODO same calculation as in pokefly @line 193 with difference of "- pointerHeight - statusBarHeight" this should be outsource in a method
        arcCenter = (int) ((displayMetrics.widthPixels * 0.5));
        arcInitialY = (int) Math.floor(displayMetrics.heightPixels / 2.803943); // - pointerHeight - statusBarHeight; // 913 - pointerHeight - statusBarHeight; //(int)Math.round(displayMetrics.heightPixels / 6.0952381) * -1; //dpToPx(113) * -1; //(int)Math.round(displayMetrics.heightPixels / 6.0952381) * -1; //-420;
        if (displayMetrics.heightPixels == 2392 || displayMetrics.heightPixels == 800) {
            arcInitialY--;
        } else if (displayMetrics.heightPixels == 1920) {
            arcInitialY++;
        }

        // TODO same calculation as in pokefly @line 201
        radius = (int) Math.round(displayMetrics.heightPixels / 4.3760683); //dpToPx(157); //(int)Math.round(displayMetrics.heightPixels / 4.37606838); //(int)Math.round(displayMetrics.widthPixels / 2.46153846); //585;
        if (displayMetrics.heightPixels == 1776 || displayMetrics.heightPixels == 960 || displayMetrics.heightPixels == 800) {
            radius++;
        }

        setupArcPoints(1);
        myArcPointer.setArcPointer(1);
    }

    public void setupArcPoints(int trainerLevel) {
        final int indices = Math.min((int)((trainerLevel + 1.5) * 2) - 1,79);
        arcX = new int[indices];
        arcY = new int[indices];

        double maxAngle = 178.4;
        double levelCoeff = maxAngle * Constants.CpM[trainerLevel * 2 - 2] / ( Constants.CpM[(int) ( (trainerLevel + 1.5)* 2 - 2 )] - Constants.CpM[0] );

        for (double pokeLevel = 1.0; pokeLevel <= trainerLevel + 1.5; pokeLevel += 0.5) {
            double angleInDegrees = (Constants.CpM[(int) (pokeLevel * 2 - 2)] - Constants.CpM[0]) * levelCoeff / Constants.CpM[trainerLevel * 2 - 2];

            double angleInRadians = (angleInDegrees + 180) * Math.PI / 180.0;

            int index = Tools.convertLevelToIndex(pokeLevel);
            arcX[index] = (int) (arcCenter + (radius * Math.cos(angleInRadians)));
            arcY[index] = (int) (arcInitialY + (radius * Math.sin(angleInRadians)));
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
