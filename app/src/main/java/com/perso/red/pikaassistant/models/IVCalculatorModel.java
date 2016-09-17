package com.perso.red.pikaassistant.models;

import com.perso.red.pikaassistant.utils.Constants;

/**
 * Created by pierr on 22/08/2016.
 */

public class IVCalculatorModel {

    private int     trainerLvl;
    private int     pokemonId;
    private String  pokemonName;
    private int     pokemonCp;
    private int     pokemonHp;
    private double  pokemonLvl;
    private String  pokemonDust;
    private boolean poweredUp;
    private int     calculatorMode;

    public IVCalculatorModel() {
        calculatorMode = Constants.CALCULATOR_MODE_ARC;
        pokemonDust = "200";
        poweredUp = false;
    }

    public int getTrainerLvl() {
        return trainerLvl;
    }

    public void setTrainerLvl(int trainerLvl) {
        this.trainerLvl = trainerLvl;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public int getPokemonId() {
        return pokemonId;
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public int getPokemonCp() {
        return pokemonCp;
    }

    public void setPokemonCp(int pokemonCp) {
        this.pokemonCp = pokemonCp;
    }

    public int getPokemonHp() {
        return pokemonHp;
    }

    public void setPokemonHp(int pokemonHp) {
        this.pokemonHp = pokemonHp;
    }

    public double getPokemonLvl() {
        return pokemonLvl;
    }

    public void setPokemonLvl(double pokemonLvl) {
        this.pokemonLvl = pokemonLvl;
    }

    public String getPokemonDust() {
        return pokemonDust;
    }

    public void setPokemonDust(String pokemonDust) {
        this.pokemonDust = pokemonDust;
    }

    public boolean isPoweredUp() {
        return poweredUp;
    }

    public void setPoweredUp(boolean poweredUp) {
        this.poweredUp = poweredUp;
    }

    public int getCalculatorMode() {
        return calculatorMode;
    }

    public void setCalculatorMode(int calculatorMode) {
        this.calculatorMode = calculatorMode;
    }
}
