package com.perso.red.pokeassistant.eggs;

import android.view.View;

import com.perso.red.pokeassistant.mainUi.MainUiPresenter;

/**
 * Created by pierr on 09/09/2016.
 */
public class EggsPresenter implements Eggs.Presenter {

    private EggsView        view;
    private MainUiPresenter presenter;

    public EggsPresenter(MainUiPresenter presenter, View layout, boolean showMenuBtn) {
        this.presenter = presenter;
        view = new EggsView(layout, this, showMenuBtn);
    }

    @Override
    public void showMenu() {
        presenter.showMenu();
    }
}
