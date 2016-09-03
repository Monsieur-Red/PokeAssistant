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
    private List<IVResult>      ivResultsFinal;

    private boolean attackCbChecked;
    private boolean defenseCbChecked;
    private boolean staminaCbChecked;

    public IVDetailsInteractor(IVCalculatorModel ivCalculatorModel, AssetManager assetManager) {
        this.ivCalculatorModel = ivCalculatorModel;
        pokemonJson = new PokemonJson(assetManager);
        ivResultsFinal = new ArrayList<>();
        attackCbChecked = false;
        defenseCbChecked = false;
        staminaCbChecked = false;
    }

    public void update(IOnIVDetailsFinishedListener listener) {
        List<IVResult>  ivResultsFilter;

        getIV();

        ivResultsFilter = filterIVs(ivResultsFinal);
        if (ivResultsFilter.size() > 0) {
            Collections.sort(ivResultsFilter);
            listener.onSuccessGuessingIV(ivResultsFilter);
        }
        else
            listener.onFailGuessingIV();
    }

    private void getIV() {
        Pokemon         pokemon = pokemonJson.getPokemons().get(ivCalculatorModel.getPokemonId());
        Stats           stats = pokemon.getStats();
        List<IVResult>  ivResults = new ArrayList<>();
        double          cpm = Constants.CpM[Tools.convertLevelToIndex(ivCalculatorModel.getPokemonLvl())];
        float           cp = ivCalculatorModel.getPokemonCp();
        int             attackBase = stats.getAttack();
        int             defenseBase = stats.getDefense();
        int             staminaBase = stats.getStamina();
        List<Integer>   staminaIVS = getStaminaIVs(ivCalculatorModel.getPokemonHp(), staminaBase, cpm);

        ivResultsFinal = new ArrayList<>();

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

    public List<IVResult>  filterIVs(List<IVResult> ivResults) {
        if (attackCbChecked || defenseCbChecked || staminaCbChecked) {
            List<IVResult>  ivResultsFilter = new ArrayList<>();

            for (IVResult ivResult : ivResults) {
                int attack = ivResult.getAttackIV();
                int defense = ivResult.getDefenseIV();
                int stamina = ivResult.getStaminaIV();

                if (attackCbChecked && !defenseCbChecked && !staminaCbChecked && attack > defense && attack > stamina)
                    ivResultsFilter.add(ivResult);
                else if (defenseCbChecked && !attackCbChecked && !staminaCbChecked && defense > attack && defense > stamina)
                    ivResultsFilter.add(ivResult);
                else if (staminaCbChecked && !attackCbChecked && !defenseCbChecked && stamina > attack && stamina > defense)
                    ivResultsFilter.add(ivResult);
                else if (attackCbChecked && defenseCbChecked && staminaCbChecked && attack == defense && attack == stamina)
                    ivResultsFilter.add(ivResult);
                else if (attackCbChecked && defenseCbChecked && !staminaCbChecked && attack == defense && attack > stamina && defense > stamina)
                    ivResultsFilter.add(ivResult);
                else if (attackCbChecked && staminaCbChecked && !defenseCbChecked && attack == stamina && attack > defense && stamina > defense)
                    ivResultsFilter.add(ivResult);
                else if (defenseCbChecked && staminaCbChecked && !attackCbChecked && defense == stamina && defense > attack && stamina > attack)
                    ivResultsFilter.add(ivResult);
            }
            return ivResultsFilter;
        }
        return ivResults;
    }

    public List<IVResult> getIvResultsFinal() {
        return ivResultsFinal;
    }

    public void setAttackCbChecked(boolean checked) {
        attackCbChecked = checked;
    }

    public void setDefenseCbChecked(boolean checked) {
        defenseCbChecked = checked;
    }

    public void setStaminaCbChecked(boolean checked) {
        staminaCbChecked = checked;
    }
}
