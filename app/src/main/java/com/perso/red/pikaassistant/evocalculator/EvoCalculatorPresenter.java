package com.perso.red.pikaassistant.evocalculator;

import android.view.View;

import com.perso.red.pikaassistant.mainUi.MainUiPresenter;
import com.perso.red.pikaassistant.models.EvoCalculatorPokemon;
import com.perso.red.pikaassistant.models.ModelManager;

import java.util.List;

/**
 * Created by pierr on 11/09/2016.
 */

public class EvoCalculatorPresenter implements EvoCalculator.Presenter, EvoCalculator.OnFinishedListener {

    private EvoCalculatorView       view;
    private EvoCalculatorInteractor interactor;
    private MainUiPresenter         mainUiPresenter;

    public EvoCalculatorPresenter(MainUiPresenter mainUiPresenter, View layout, ModelManager modelManager, boolean showMenuBtn) {
        this.mainUiPresenter = mainUiPresenter;
        interactor = new EvoCalculatorInteractor(modelManager);
        view = new EvoCalculatorView(layout, this, showMenuBtn);
    }

    @Override
    public void update(String name, String cp) {
        int id = interactor.checkName(name);

        if (id != -1)
            interactor.update(this, id, name, cp);
    }

    @Override
    public void hideResult() {
        view.hideResult();
    }

    @Override
    public void showMenu() {
        mainUiPresenter.showMenu();
    }

    @Override
    public void OnSuccessUpdate(List<EvoCalculatorPokemon> pokemons) {
        if (pokemons.size() > 0)
            view.setResult(pokemons);
        else
            view.hideResult();
    }

    public List<String> getPokemonNamesWithEvolution() {
        return interactor.getPokemonNamesWithEvolution();
    }
}
