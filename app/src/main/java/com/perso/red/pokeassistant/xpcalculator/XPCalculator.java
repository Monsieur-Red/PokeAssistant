package com.perso.red.pokeassistant.xpcalculator;

import com.perso.red.pokeassistant.models.XPCalculatorPokemon;
import com.perso.red.pokeassistant.models.XPCalculatorResult;

import java.util.List;

/**
 * Created by pierr on 10/09/2016.
 */

public interface XPCalculator {

    interface View {
        void setResult(int experience, int time, boolean luckyEgg, String transfert, String evolve);
        void hideResult();
    }

    interface Presenter {
        boolean checkPokemonName(String pokemon);
        void update(List<XPCalculatorPokemon> pokemons);
        void hideResult();
        void showMenu();
    }

    interface OnFinishedListener {
        void OnSuccessUpdate(int experience, int time, boolean luckyEgg, List<XPCalculatorResult> results);
    }

}
