package com.perso.red.pokeassistant.xpcalculator;

import android.text.TextUtils;
import android.view.View;

import com.perso.red.pokeassistant.mainUi.MainUiPresenter;
import com.perso.red.pokeassistant.models.ModelManager;
import com.perso.red.pokeassistant.models.XPCalculatorPokemon;
import com.perso.red.pokeassistant.models.XPCalculatorResult;

import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 10/09/2016.
 */

public class XPCalculatorPresenter implements XPCalculator.Presenter, XPCalculator.OnFinishedListener {

    private XPCalculatorView        view;
    private XPCalculatorInteractor  interactor;
    private MainUiPresenter         mainUiPresenter;

    public XPCalculatorPresenter(MainUiPresenter mainUiPresenter, View layout, ModelManager modelManager) {
        this.mainUiPresenter = mainUiPresenter;
        interactor = new XPCalculatorInteractor(modelManager);
        view = new XPCalculatorView(layout, this);
    }


    @Override
    public boolean checkPokemonName(String pokemon) {
        boolean matched = false;

        for (String name : interactor.getPokemonNamesWithEvolution()) {
            if (name.equals(pokemon)) {
                matched = true;
                break;
            }
        }
        return matched;
    }

    @Override
    public void update(List<XPCalculatorPokemon> pokemons) {
        interactor.update(this, pokemons);
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
    public void OnSuccessUpdate(int experience, int time, boolean luckyEgg, List<XPCalculatorResult> results) {
        String    transfert = "";
        String    evolve = "";

        for (XPCalculatorResult result : results) {
            int transfertInt = result.getTransferCount();
            int evolveInt = result.getEvolveCount();
            int timeInt = result.getEvolveTime();

            if (transfertInt > 0) {
                if (!TextUtils.isEmpty(transfert))
                    transfert += "\n";

                transfert += "Transfert " + String.valueOf(transfertInt) + " " + result.getPokemonName();
            }

            if (evolveInt > 0) {
                String  timeStr;

                if (!TextUtils.isEmpty(evolve))
                    evolve += "\n";

                if (time >= 120)
                    timeStr = String.format(Locale.getDefault(), "%d:%02d", timeInt / 60, timeInt % 60) + " h";
                else if (timeInt >= 60)
                    timeStr = String.format(Locale.getDefault(), "%d:%02d", timeInt / 60, timeInt % 60) + " h";
                else
                    timeStr = String.format(Locale.getDefault(), "%02d", timeInt % 60) + " min";

                evolve += String.valueOf(evolveInt) + " " + result.getPokemonName() + " -> " + result.getXp() + " XP -> " + timeStr;
            }
        }
        view.setResult(experience, time, luckyEgg, transfert, evolve);
    }

    public List<String> getPokemonNamesWithEvolution() {
        return interactor.getPokemonNamesWithEvolution();
    }

    public void setLuckyEgg(boolean luckyEgg) {
        interactor.setLuckyEgg(luckyEgg);
    }
}
