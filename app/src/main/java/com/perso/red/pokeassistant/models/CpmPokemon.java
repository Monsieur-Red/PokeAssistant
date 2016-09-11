package com.perso.red.pokeassistant.models;

import android.content.res.AssetManager;

import com.perso.red.pokeassistant.utils.Constants;
import com.perso.red.pokeassistant.utils.Tools;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pierr on 11/09/2016.
 */

public class CpmPokemon {

    private HashMap<String, List<Double>> data;

    public CpmPokemon(AssetManager assetManager) {
        data = new HashMap<>();

        try {
            String json = Tools.loadJSONFromAsset(assetManager, Constants.FILE_CPM_POKEMON);

            JSONObject          jsonObject = new JSONObject(json);
            Iterator<String>    keys = jsonObject.keys();

            while (keys.hasNext()) {
                String          key = keys.next();
                JSONArray       value = jsonObject.getJSONArray(key);
                List<Double>    list = new ArrayList<>();

                for (int i = 0; i < value.length(); i++) {
                    list.add(value.getDouble(i));
                }

                data.put(key, list);
            }
        }  catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Double> getPokemonCpm(String name) {
        return data.get(name);
    }
}
