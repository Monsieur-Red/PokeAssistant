package com.perso.red.pikaassistant.models;

import android.content.res.AssetManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 10/09/2016.
 */

public class ModelManager {

    private PokemonJson     pokemonJson;
    private List<String>    pokemonNames;

    private List<Pokemon>   pokemonWithEvolution;
    private List<String>    pokemonNamesWithEvolution;

    private CpmPokemon      cpmPokemon;
    private MaxCpPokemon    maxCpPokemon;

    public ModelManager(AssetManager assetManager) {
        pokemonJson = new PokemonJson(assetManager);
        pokemonNames = new PokemonNames(assetManager).getlist();
        initPokemonWithEvolution();
        cpmPokemon = new CpmPokemon(assetManager);
        maxCpPokemon = new MaxCpPokemon(assetManager);
        setPokemonJsonMoveSets(assetManager);
    }

    private void initPokemonWithEvolution() {
        List<Pokemon>   pokemons = pokemonJson.getPokemons();
        pokemonWithEvolution = new ArrayList<>();
        pokemonNamesWithEvolution = new ArrayList<>();

        for (int i = 0; i < pokemons.size(); i++) {
            Pokemon pokemon = pokemons.get(i);

            if (pokemon.getCandy() != 0) {
                pokemonWithEvolution.add(pokemon);
                pokemonNamesWithEvolution.add(pokemonNames.get(i));
            }
        }
    }

    private void setPokemonJsonMoveSets(AssetManager assetManager) {
        MoveSetsJson moveSetsJson = new MoveSetsJson(assetManager);

        for (Pokemon pokemon : pokemonJson.getPokemons()) {
            for (Move move1 : pokemon.getMoves1()) {
                String  move = move1.getName();
                move = move.replace("FAST", "").replace("_", "");
                move1.setName(moveSetsJson.getMove(move));
            }

            for (Move move2 : pokemon.getMoves2()) {
                String  move = move2.getName();
                move = move.replace("_", "");
                move2.setName(moveSetsJson.getMove(move));
            }
        }
    }

    public PokemonJson getPokemonJson() {
        return pokemonJson;
    }

    public List<String> getPokemonNames() {
        return pokemonNames;
    }

    public List<Pokemon> getPokemonWithEvolution() {
        return pokemonWithEvolution;
    }

    public List<String> getPokemonNamesWithEvolution() {
        return pokemonNamesWithEvolution;
    }

    public CpmPokemon getCpmPokemon() {
        return cpmPokemon;
    }

    public MaxCpPokemon getMaxCpPokemon() {
        return maxCpPokemon;
    }
}
