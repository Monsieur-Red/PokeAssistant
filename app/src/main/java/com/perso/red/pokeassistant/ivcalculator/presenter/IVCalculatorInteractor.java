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

    public IVCalculatorInteractor(AssetManager assetManager) {
        ivCalculatorModel = new IVCalculatorModel();
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

    public void setPokemonDust(String dust) {
        ivCalculatorModel.setPokemonDust(dust);
    }

    public void setPokemonPoweredUp(boolean poweredUp) {
        ivCalculatorModel.setPoweredUp(poweredUp);
    }

    public void setCalculatorMode(int mode) {
        ivCalculatorModel.setCalculatorMode(mode);
    }

    public List<String> getPokemonNames() {
        return pokemonNames.getlist();
    }

    public IVCalculatorModel getIvCalculatorModel() {
        return ivCalculatorModel;
    }
}
