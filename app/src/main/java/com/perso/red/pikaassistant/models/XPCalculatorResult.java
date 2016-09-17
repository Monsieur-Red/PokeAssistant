package com.perso.red.pikaassistant.models;

/**
 * Created by pierr on 10/09/2016.
 */

public class XPCalculatorResult {

    private String  pokemonName;
    private int     transferCount;
    private int     evolveCount;
    private int     evolveTime;
    private int     candyRemaining;
    private int     pokemonRemaining;
    private int     xp;

    public XPCalculatorResult(String pokemonName, int transferCount, int evolveCount, int evolveTime, int candyRemaining, int pokemonRemaining, int xp) {
        this.pokemonName = pokemonName;
        this.transferCount = transferCount;
        this.evolveCount = evolveCount;
        this.evolveTime = evolveTime;
        this.candyRemaining = candyRemaining;
        this.pokemonRemaining = pokemonRemaining;
        this.xp = xp;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public int getTransferCount() {
        return transferCount;
    }

    public int getEvolveCount() {
        return evolveCount;
    }

    public int getEvolveTime() {
        return evolveTime;
    }

    public int getCandyRemaining() {
        return candyRemaining;
    }

    public int getPokemonRemaining() {
        return pokemonRemaining;
    }

    public int getXp() {
        return xp;
    }
}
