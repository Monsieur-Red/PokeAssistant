package com.perso.red.pokeassistant.appraisal;

import android.view.View;

import com.perso.red.pokeassistant.mainUi.MainUiPresenter;

/**
 * Created by pierr on 09/09/2016.
 */

public class AppraisalPresenter implements Appraisal.Presenter {

    private AppraisalView   view;
    private MainUiPresenter mainUiPresenter;

    public AppraisalPresenter(MainUiPresenter mainUiPresenter, View layout, boolean showMenuBtn) {
        this.mainUiPresenter = mainUiPresenter;
        view = new AppraisalView(layout, this, showMenuBtn);
    }

    @Override
    public void showMenu() {
        mainUiPresenter.showMenu();
    }
}
