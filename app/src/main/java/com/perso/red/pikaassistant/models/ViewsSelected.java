package com.perso.red.pikaassistant.models;

import android.content.SharedPreferences;

import com.perso.red.pikaassistant.utils.Constants;

import java.io.Serializable;

/**
 * Created by pierr on 16/09/2016.
 */

public class ViewsSelected implements Serializable {

    private boolean ivCalculator;
    private boolean xpCalculator;
    private boolean evolutionCalculator;
    private boolean eggs;
    private boolean buddies;
    private boolean appraisal;

    public ViewsSelected(SharedPreferences sharedPreferences) {
        ivCalculator = sharedPreferences.getBoolean(Constants.VIEW_SELECTED_IV_CALC, false);
        xpCalculator = sharedPreferences.getBoolean(Constants.VIEW_SELECTED_XP_CALC, false);
        evolutionCalculator = sharedPreferences.getBoolean(Constants.VIEW_SELECTED_EVO_CALC, false);
        eggs = sharedPreferences.getBoolean(Constants.VIEW_SELECTED_EGGS, false);
        buddies = sharedPreferences.getBoolean(Constants.VIEW_SELECTED_BUDDIES, false);
        appraisal = sharedPreferences.getBoolean(Constants.VIEW_SELECTED_APPRAISAL, false);
    }

    public int size() {
        int size = 0;

        if (ivCalculator)
            size++;
        if (xpCalculator)
            size++;
        if (evolutionCalculator)
            size++;
        if (eggs)
            size++;
        if (buddies)
            size++;
        if (appraisal)
            size++;

        return size;
    }

    public boolean isIvCalculator() {
        return ivCalculator;
    }

    public void setIvCalculator(boolean ivCalculator) {
        this.ivCalculator = ivCalculator;
    }

    public boolean isXpCalculator() {
        return xpCalculator;
    }

    public void setXpCalculator(boolean xpCalculator) {
        this.xpCalculator = xpCalculator;
    }

    public boolean isEvolutionCalculator() {
        return evolutionCalculator;
    }

    public void setEvolutionCalculator(boolean evolutionCalculator) {
        this.evolutionCalculator = evolutionCalculator;
    }

    public boolean isEggs() {
        return eggs;
    }

    public void setEggs(boolean eggs) {
        this.eggs = eggs;
    }

    public boolean isBuddies() {
        return buddies;
    }

    public void setBuddies(boolean buddies) {
        this.buddies = buddies;
    }

    public boolean isAppraisal() {
        return appraisal;
    }

    public void setAppraisal(boolean appraisal) {
        this.appraisal = appraisal;
    }
}
