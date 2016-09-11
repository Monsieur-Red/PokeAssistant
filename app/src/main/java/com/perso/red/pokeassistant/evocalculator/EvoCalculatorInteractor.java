package com.perso.red.pokeassistant.evocalculator;

import com.perso.red.pokeassistant.models.CpmPokemon;
import com.perso.red.pokeassistant.models.EvoCalculatorPokemon;
import com.perso.red.pokeassistant.models.MaxCpPokemon;
import com.perso.red.pokeassistant.models.ModelManager;
import com.perso.red.pokeassistant.models.Pokemon;
import com.perso.red.pokeassistant.models.PokemonJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 11/09/2016.
 */

public class EvoCalculatorInteractor {

    private PokemonJson     pokemonJson;
    private List<String>    pokemonNames;
    private List<Pokemon>   pokemonWithEvolution;
    private List<String>    pokemonNamesWithEvolution;
    private CpmPokemon      cpmPokemon;
    private MaxCpPokemon    maxCpPokemon;

    public EvoCalculatorInteractor(ModelManager modelManager) {
        pokemonJson = modelManager.getPokemonJson();
        pokemonNames = modelManager.getPokemonNames();
        pokemonWithEvolution = modelManager.getPokemonWithEvolution();
        pokemonNamesWithEvolution = modelManager.getPokemonNamesWithEvolution();
        cpmPokemon = modelManager.getCpmPokemon();
        maxCpPokemon = modelManager.getMaxCpPokemon();
    }

    public void update(EvoCalculator.OnFinishedListener listener, int id, String name, String cp) {
        List<EvoCalculatorPokemon>  result = new ArrayList<>();
        Pokemon                     pokemon1 = pokemonWithEvolution.get(id);
        List<Pokemon>               familyPoke = pokemonJson.getPokemonsFamily(pokemon1.getId() - 1, pokemon1.getFamily());

        Pokemon previousPokemon = null;
        for (int i = 0; i < familyPoke.size(); i++) {
            Pokemon pokemon = familyPoke.get(i);
            String  pokemonMaxCp = String.valueOf((int)maxCpPokemon.getMaxCpPokemon(pokemon.getName()));
            String  newCp = cp;

            if (i != 0)
                newCp = getCp(cp, cpmPokemon.getPokemonCpm(previousPokemon.getName()));

            result.add(new EvoCalculatorPokemon(pokemonNames.get(pokemon.getId() - 1), newCp, pokemonMaxCp));
            previousPokemon = pokemon;
        }

        listener.OnSuccessUpdate(result);
    }

    private String getCp(String cpStr, List<Double> cpm) {
        String  result;
        int     cp = Integer.valueOf(cpStr);
        int     minCp = (int)(cp * cpm.get(0));
        int     maxCp = (int)(cp * cpm.get(1));

        result = String.valueOf(minCp) + " - " + String.valueOf(maxCp);
        return result;
    }

    public int checkName(String pokemon) {
        int id = -1;

        for (int i = 0; i < pokemonNamesWithEvolution.size(); i++) {
            if (pokemonNamesWithEvolution.get(i).equals(pokemon)) {
                id = i;
                break;
            }
        }
        return id;
    }

    public List<String> getPokemonNamesWithEvolution() {
        return pokemonNamesWithEvolution;
    }
}
