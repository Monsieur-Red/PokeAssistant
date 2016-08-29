package com.perso.red.pokeassistant.models;

import android.content.res.AssetManager;

import com.google.gson.reflect.TypeToken;
import com.perso.red.pokeassistant.utils.Constants;
import com.perso.red.pokeassistant.utils.Tools;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 22/08/2016.
 */

public class PokemonNames {

    private List<String> list;

    public PokemonNames(AssetManager assetManager) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<List>  jsonAdapter = moshi.adapter(new TypeToken<List<String>>(){}.getType());

        try {
            if (Locale.getDefault().getDisplayLanguage().equals(Constants.LANGUAGE_FRENCH))
                list = jsonAdapter.fromJson(Tools.loadJSONFromAsset(assetManager, Constants.FILE_POKEMON_FR));
            else
                list = jsonAdapter.fromJson(Tools.loadJSONFromAsset(assetManager, Constants.FILE_POKEMON_EN));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getlist() {
        return list;
    }
}
