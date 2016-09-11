package com.perso.red.pokeassistant.models;

/**
 * Created by pierr on 11/09/2016.
 */

public class EvoCalculatorPokemon {

    private String  name;
    private String  cp;
    private String  maxCp;

    public EvoCalculatorPokemon(String name, String cp, String maxCp) {
        this.name = name;
        this.cp = cp;
        this.maxCp = maxCp;
    }

    public String getName() {
        return name;
    }

    public String getCp() {
        return cp;
    }

    public String getMaxCp() {
        return maxCp;
    }
}
