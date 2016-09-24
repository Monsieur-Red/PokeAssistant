package com.perso.red.pikaassistant.models;

import com.google.gson.annotations.SerializedName;
import com.squareup.moshi.Json;

/**
 * Created by pierr on 23/09/2016.
 */

public class MoveSet {

    private int     id;
    @Json(name = "English")
    private String  en;
    @Json(name = "Japanese Kana")
    private String  jaKana;
    @Json(name = "Japanese Rōmaji")
    private String  jaRomaji;
    @Json(name = "French")
    private String  fr;
    @Json(name = "German")
    private String  de;
    @Json(name = "Italian")
    private String  it;
    @Json(name = "Spanish")
    private String  sp;
    @Json(name = "Korean Hangul")
    private String  koHangul;
    @Json(name = "Korean Romanized")
    private String  koRomanized;

    public int getId() {
        return id;
    }

    public String getEn() {
        return en;
    }

    public String getJaKana() {
        return jaKana;
    }

    public String getJaRomaji() {
        return jaRomaji;
    }

    public String getFr() {
        return fr;
    }

    public String getDe() {
        return de;
    }

    public String getIt() {
        return it;
    }

    public String getSp() {
        return sp;
    }

    public String getKoHangul() {
        return koHangul;
    }

    public String getKoRomanized() {
        return koRomanized;
    }
}
