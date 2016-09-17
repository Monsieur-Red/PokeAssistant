package com.perso.red.pikaassistant.xpcalculator;

import com.perso.red.pikaassistant.models.ModelManager;
import com.perso.red.pikaassistant.models.Pokemon;
import com.perso.red.pikaassistant.models.XPCalculatorPokemon;
import com.perso.red.pikaassistant.models.XPCalculatorResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 10/09/2016.
 */

public class XPCalculatorInteractor {

    private List<Pokemon>   pokemonWithEvolution;
    private List<String>    pokemonNamesWithEvolution;

    private boolean luckyEgg;

    public XPCalculatorInteractor(ModelManager modelManager) {
        pokemonWithEvolution = modelManager.getPokemonWithEvolution();
        pokemonNamesWithEvolution = modelManager.getPokemonNamesWithEvolution();
        luckyEgg = false;
    }

    public void update(XPCalculator.OnFinishedListener listener, List<XPCalculatorPokemon> pokemonsExp) {
        List<XPCalculatorResult>    results = new ArrayList<>();
        int xpTotal = 0;
        int timteTotal = 0;

        for (XPCalculatorPokemon pokemonExp : pokemonsExp) {
            int id = getPokemonId(pokemonExp.getName());

            if (id != -1) {
                Pokemon pokemon = pokemonWithEvolution.get(id);
                int     pokemonAmount = pokemonExp.getAmount();
                int     candyAmount = pokemonExp.getCandy();
                int     candyCost = pokemon.getCandy();
                int     evolveCount = 0;
                int     transferCount = 0;
                boolean canStillEvolve = true;

                while (canStillEvolve) {
                    if (Math.floor(candyAmount / candyCost) == 0 || pokemonAmount == 0)
                        canStillEvolve = false;
                    else {
                        pokemonAmount--;
                        candyAmount -= candyCost;
                        candyAmount++;
                        evolveCount++;

                        if (pokemonAmount == 0)
                            break;
                    }
                }

                boolean shouldTransfer = true;
                while (shouldTransfer) {
                    if ((candyAmount + pokemonAmount) < (candyCost + 1) || pokemonAmount == 0) {
                        shouldTransfer = false;
                        break;
                    }

                    while (candyAmount < candyCost) {
                        transferCount++;
                        pokemonAmount--;
                        candyAmount++;
                    }

                    pokemonAmount--;
                    candyAmount -= candyCost;
                    candyAmount++;
                    evolveCount++;
                }

                int     xp;
//                double  eggsToUse = Math.floor((evolveCount * 30 / 60) / 30);
                int     evolveTime = (evolveCount * 30 / 60);

                if (luckyEgg)
                    xp = evolveCount * 1000;
                else
                    xp = evolveCount * 500;

                xpTotal += xp;
                timteTotal += evolveTime;
                results.add(new XPCalculatorResult(pokemonNamesWithEvolution.get(id), transferCount, evolveCount, evolveTime, candyAmount, pokemonAmount, xp));
            }
        }

        if (results.size() > 0) {
            listener.OnSuccessUpdate(xpTotal, timteTotal, luckyEgg, results);
        }
    }

    private int getPokemonId(String name) {
        int id = -1;

        for (int i = 0;  i < pokemonNamesWithEvolution.size(); i++) {
            String  pokemonName = pokemonNamesWithEvolution.get(i);

            if (pokemonName.equals(name)) {
                id = i;
                break;
            }
        }
        return id;
    }

    public List<String> getPokemonNamesWithEvolution() {
        return pokemonNamesWithEvolution;
    }

    public void setLuckyEgg(boolean luckyEgg) {
        this.luckyEgg = luckyEgg;
    }
}
