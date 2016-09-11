package com.perso.red.pokeassistant.models;

/**
 * Created by pierr on 10/09/2016.
 */

public class XPCalculatorPokemon {

    private String  name;
    private int     amount;
    private int     candy;

    public XPCalculatorPokemon(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCandy() {
        return candy;
    }

    public void setCandy(int candy) {
        this.candy = candy;
    }
}
