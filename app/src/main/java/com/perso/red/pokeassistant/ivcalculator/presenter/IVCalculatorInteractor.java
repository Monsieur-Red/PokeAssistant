package com.perso.red.pokeassistant.ivcalculator.presenter;

import android.content.res.AssetManager;

import com.perso.red.pokeassistant.models.IVCalculatorModel;
import com.perso.red.pokeassistant.models.PokemonNames;

import java.util.List;

/**
 * Created by pierr on 20/08/2016.
 */

public class IVCalculatorInteractor {

    private IVCalculatorModel   ivCalculatorModel;
    private PokemonNames        pokemonNames;

    public IVCalculatorInteractor(IVCalculatorModel ivCalculatorModel, AssetManager assetManager) {
        this.ivCalculatorModel = ivCalculatorModel;
        pokemonNames = new PokemonNames(assetManager);
    }

    public  void setTrainerLvl(int trainerLvl) {
        ivCalculatorModel.setTrainerLvl(trainerLvl);
    }

    public void setPokemonName(String name, IOnIVCalculatorFinishedListener listener) {
        ivCalculatorModel.setPokemonId(pokemonNames.getlist().indexOf(name));
        ivCalculatorModel.setPokemonName(name);
        listener.onSuccessSetNamePokemon(ivCalculatorModel.getPokemonId());
    }

    public void setPokemonCP(int cp) {
        ivCalculatorModel.setPokemonCp(cp);
    }

    public void setPokemonHP(int hp) {
        ivCalculatorModel.setPokemonHp(hp);
    }

    public void setPokemonLvl(double lvl) {
        ivCalculatorModel.setPokemonLvl(lvl);
    }

    public List<String> getPokemonNames() {
        return pokemonNames.getlist();
    }
}
