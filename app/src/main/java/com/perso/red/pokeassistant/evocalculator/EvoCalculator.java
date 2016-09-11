package com.perso.red.pokeassistant.evocalculator;

import com.perso.red.pokeassistant.models.EvoCalculatorPokemon;

import java.util.List;

/**
 * Created by pierr on 11/09/2016.
 */

public interface EvoCalculator {
    interface View {
        void setResult(List<EvoCalculatorPokemon> pokemons);
        void hideResult();
    }

    interface Presenter {
        void update(String name, String cp);
        void hideResult();
        void showMenu();
    }

    interface OnFinishedListener {
        void OnSuccessUpdate(List<EvoCalculatorPokemon> pokemons);
    }


}
