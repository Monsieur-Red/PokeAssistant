package com.perso.red.pikaassistant.pokedex;

import android.content.res.AssetManager;

import com.perso.red.pikaassistant.models.PokemonNames;

import java.util.List;

/**
 * Created by pierr on 09/09/2016.
 */
public class PokedexInteractor {

    private PokemonNames pokemonNames;

    public PokedexInteractor(AssetManager assetManager) {
        pokemonNames = new PokemonNames(assetManager);

    }

    public List<String> getPokemonNames() {
        return pokemonNames.getlist();
    }
}
