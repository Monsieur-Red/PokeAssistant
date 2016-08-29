package com.perso.red.pokeassistant.ivdetails.presenter;

import android.content.res.AssetManager;

import com.perso.red.pokeassistant.models.IVCalculatorModel;
import com.perso.red.pokeassistant.models.IVResult;
import com.perso.red.pokeassistant.models.Pokemon;
import com.perso.red.pokeassistant.models.PokemonJson;
import com.perso.red.pokeassistant.models.Stats;
import com.perso.red.pokeassistant.utils.Constants;
import com.perso.red.pokeassistant.utils.Tools;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pierr on 22/08/2016.
 */

public class IVDetailsInteractor {

    private IVCalculatorModel   ivCalculatorModel;
    private PokemonJson         pokemonJson;

    public IVDetailsInteractor(IVCalculatorModel ivCalculatorModel, AssetManager assetManager) {
        this.ivCalculatorModel = ivCalculatorModel;
        pokemonJson = new PokemonJson(assetManager);
    }

    public void update(IOnIVDetailsFinishedListener listener) {
        if (ivCalculatorModel.getPokemonCp() > 0)
            getIV(listener);
    }

    private void getIV(IOnIVDetailsFinishedListener listener) {
        Pokemon         pokemon = pokemonJson.getPokemons().get(ivCalculatorModel.getPokemonId());
        Stats           stats = pokemon.getStats();
        List<IVResult>  ivResults = new ArrayList<>();
        List<IVResult>  ivResultsFinal = new ArrayList<>();
        double          cpm = Constants.CpM[Tools.convertLevelToIndex(ivCalculatorModel.getPokemonLvl())];
        float           cp = ivCalculatorModel.getPokemonCp();
        int             attackBase = stats.getAttack();
        int             defenseBase = stats.getDefense();
        int             staminaBase = stats.getStamina();
        List<Integer>   staminaIVS = getStaminaIVs(ivCalculatorModel.getPokemonHp(), staminaBase, cpm);

        // Get Attack Iv
        for (int ivDefense = 0; ivDefense <= 15; ivDefense++) {
            for (int staminaIV : staminaIVS) {
                int ivAttack = (int)Math.ceil(cp / ((Math.pow(defenseBase + ivDefense, 0.5) * Math.pow(staminaBase + staminaIV, 0.5) * Math.pow(cpm, 2) / 10)) - attackBase);

                if (ivAttack >= 0 && ivAttack <= 15)
                    ivResults.add(new IVResult(ivAttack, ivDefense, staminaIV));
            }
        }

        for (IVResult result : ivResults) {
            int cpResult = (int)((attackBase + result.getAttackIV()) * ((Math.pow(defenseBase + result.getDefenseIV(), 0.5) * Math.pow(staminaBase + result.getStaminaIV(), 0.5) * Math.pow(cpm, 2) / 10)));

            if (cpResult == cp)
                ivResultsFinal.add(result);
        }

        if (ivResultsFinal.size() > 0) {
            Collections.sort(ivResultsFinal);
            listener.onSuccessGuessingIV(ivResultsFinal);
        }
        else
            listener.onFailGuessingIV();
    }

    private List<Integer>   getStaminaIVs(int hp, int staminaBase, double cpm) {
        List<Integer>   staminaIVs = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            double result = Math.floor((staminaBase + i) * cpm);

            if (result == hp)
                staminaIVs.add(i);
        }
        return (staminaIVs);
    }
}
