package com.perso.red.pokeassistant.mainUi;

import android.view.ViewGroup;
import android.view.WindowManager;

/**
 * Created by pierr on 09/09/2016.
 */

public interface MainUi {

    interface View {
        void setVisibility();
        void removeView(WindowManager windowManager);
    }

    interface Presenter {
        void setVisibility();
        void showMenu();
        void back();
        void showIvCalculator(ViewGroup ivCalculatorView);
        void showEggs(ViewGroup eggsView);
        void showAppraisal(ViewGroup appraisalView);
        void showXPCalculator(ViewGroup xpCalculatorView);
        void showEvoCalculator(ViewGroup evoCalculatorView);
    }
}
