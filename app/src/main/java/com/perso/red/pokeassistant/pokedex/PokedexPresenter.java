package com.perso.red.pokeassistant.pokedex;

import android.view.View;

import com.perso.red.pokeassistant.mainUi.MainUiPresenter;

import java.util.List;

/**
 * Created by pierr on 09/09/2016.
 */

public class PokedexPresenter implements Pokedex.Presenter {

    private PokedexView view;
    private PokedexInteractor interactor;
    private MainUiPresenter     mainUiPresenter;

    public PokedexPresenter(MainUiPresenter mainUiPresenter, View layout) {
        view = new PokedexView(layout, this);
        interactor = new PokedexInteractor(layout.getContext().getAssets());
    }


    @Override
    public void showMenu() {
        mainUiPresenter.showMenu();
    }

    public List<String> getPokemonNames() {
        return interactor.getPokemonNames();
    }

}
