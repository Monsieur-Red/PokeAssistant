package com.perso.red.pikaassistant.models;

import android.content.res.AssetManager;

import com.google.gson.reflect.TypeToken;
import com.perso.red.pikaassistant.utils.Constants;
import com.perso.red.pikaassistant.utils.Tools;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pierr on 23/08/2016.
 */

public class PokemonJson {

    private List pokemons;

    public PokemonJson(AssetManager assetManager) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<List> jsonAdapterPEN = moshi.adapter(new TypeToken<List<Pokemon>>(){}.getType());

        try {
            pokemons = jsonAdapterPEN.fromJson(Tools.loadJSONFromAsset(assetManager, Constants.FILE_POKEMON));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Pokemon> getPokemons() {
        return pokemons;
    }

    public List<Pokemon> getPokemonsFamily(int id, String family) {
        List<Pokemon>   familyPoke = new ArrayList<>();

        for (int i = id; i < pokemons.size(); i++) {
            Pokemon pokemon = (Pokemon) pokemons.get(i);

            if (pokemon.getFamily().equals(family))
                familyPoke.add(pokemon);
        }

        return familyPoke;
    }
}
