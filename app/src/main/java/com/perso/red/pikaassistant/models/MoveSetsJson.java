package com.perso.red.pikaassistant.models;

import android.content.res.AssetManager;

import com.google.gson.reflect.TypeToken;
import com.perso.red.pikaassistant.utils.Constants;
import com.perso.red.pikaassistant.utils.Tools;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 23/09/2016.
 */

public class MoveSetsJson {

    private HashMap<String, String> data;

    @SuppressWarnings("unchecked")
    public MoveSetsJson(AssetManager assetManager) {
        List<MoveSet>       moveSets = null;
        String              lang = Locale.getDefault().getLanguage();
        Moshi               moshi = new Moshi.Builder().build();
        JsonAdapter<List>   jsonAdapterPEN = moshi.adapter(new TypeToken<List<MoveSet>>(){}.getType());

        try {
            moveSets = jsonAdapterPEN.fromJson(Tools.loadJSONFromAsset(assetManager, Constants.FILE_MOVE_SETS));
        } catch (IOException e) {
            e.printStackTrace();
        }

        data = new HashMap<>();

        if (moveSets != null) {
            for (MoveSet moveSet : moveSets) {
                switch (lang) {
                    case "en":
                        data.put(moveSet.getEn().replace(" ", "").toUpperCase(), moveSet.getEn());
                        break;
                    case "ja":
                        data.put(moveSet.getEn().replace(" ", "").toUpperCase(), moveSet.getJaKana());
                        break;
                    case "fr":
                        data.put(moveSet.getEn().replace(" ", "").toUpperCase(), moveSet.getFr());
                        break;
                    case "de":
                        data.put(moveSet.getEn().replace(" ", "").toUpperCase(), moveSet.getDe());
                        break;
                    case "it":
                        data.put(moveSet.getEn().replace(" ", "").toUpperCase(), moveSet.getIt());
                    break;
                    case "es":
                        data.put(moveSet.getEn().replace(" ", "").toUpperCase(), moveSet.getSp());
                        break;
                    case "ko":
                        data.put(moveSet.getEn().replace(" ", "").toUpperCase(), moveSet.getKoHangul());
                        break;
                    default:
                        data.put(moveSet.getEn().replace(" ", "").toUpperCase(), moveSet.getEn());
                        break;
                }
            }
        }

        System.out.println("*******************************DATA 0 " + data.get("Tackle"));

    }

    public String getMove(String name) {
        return data.get(name);
    }

}
