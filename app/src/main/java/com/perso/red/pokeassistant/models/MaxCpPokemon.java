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

public class MaxCpPokemon {

    private HashMap<String, Double> data;

    public MaxCpPokemon(AssetManager assetManager) {
        data = new HashMap<>();

        try {
            String json = Tools.loadJSONFromAsset(assetManager, Constants.FILE_MAX_CP_POKEMON);

            JSONObject jsonObject = new JSONObject(json);
            Iterator<String> keys = jsonObject.keys();

            while (keys.hasNext()) {
                String          key = keys.next();

                data.put(key, jsonObject.getDouble(key));
            }
        }  catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public double getMaxCpPokemon(String name) {
        return data.get(name);
    }
}
