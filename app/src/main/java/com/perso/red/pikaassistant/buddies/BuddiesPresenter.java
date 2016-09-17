package com.perso.red.pikaassistant.buddies;

import android.view.View;

import com.perso.red.pikaassistant.mainUi.MainUiPresenter;

/**
 * Created by pierr on 17/09/2016.
 */

public class BuddiesPresenter implements Buddies.Presenter {

    private MainUiPresenter presenter;

    public BuddiesPresenter(MainUiPresenter presenter, View layout, boolean showMenuBtn) {
        this.presenter = presenter;
        new BuddiesView(layout, this, showMenuBtn);
    }

    @Override
    public void showMenu() {
        presenter.showMenu();
    }
}
