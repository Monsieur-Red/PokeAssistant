package com.perso.red.pokeassistant.models;

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
}
