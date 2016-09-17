package com.perso.red.pikaassistant.eggs;

import android.view.View;

import com.perso.red.pikaassistant.mainUi.MainUiPresenter;

/**
 * Created by pierr on 09/09/2016.
 */
public class EggsPresenter implements Eggs.Presenter {

    private MainUiPresenter presenter;

    public EggsPresenter(MainUiPresenter presenter, View layout, boolean showMenuBtn) {
        this.presenter = presenter;
        new EggsView(layout, this, showMenuBtn);
    }

    @Override
    public void showMenu() {
        presenter.showMenu();
    }
}
