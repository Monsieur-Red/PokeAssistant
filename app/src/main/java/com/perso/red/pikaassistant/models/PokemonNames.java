package com.perso.red.pikaassistant.models;

import android.content.res.AssetManager;

import com.google.gson.reflect.TypeToken;
import com.perso.red.pikaassistant.utils.Constants;
import com.perso.red.pikaassistant.utils.Tools;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 22/08/2016.
 */

public class PokemonNames {

    private List    list;

    public PokemonNames(AssetManager assetManager) {
        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<List>  jsonAdapter = moshi.adapter(new TypeToken<List<String>>(){}.getType());

        try {
            String  file;

            switch (Locale.getDefault().getLanguage()) {
                case Constants.LANGUAGE_FR:
                    file = Constants.FILE_POKEMON_FR;
                    break;
                case Constants.LANGUAGE_EN:
                    file = Constants.FILE_POKEMON_EN;
                    break;
                case Constants.LANGUAGE_DE:
                    file = Constants.FILE_POKEMON_DE;
                    break;
                case Constants.LANGUAGE_JA:
                    file = Constants.FILE_POKEMON_JA;
                    break;
                case Constants.LANGUAGE_RU:
                    file = Constants.FILE_POKEMON_RU;
                    break;
                default:
                    file = Constants.FILE_POKEMON_EN;
                    break;
            }

            String json = Tools.loadJSONFromAsset(assetManager, file);
            if (json != null)
                list = jsonAdapter.fromJson(json);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getlist() {
        return list;
    }
}
