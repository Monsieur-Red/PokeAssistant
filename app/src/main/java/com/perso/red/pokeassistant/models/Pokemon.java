package com.perso.red.pokeassistant.models;

import java.util.List;

/**
 * Created by pierr on 23/08/2016.
 */

public class Pokemon {

    private int         id;
    private String      name;
    private Stats       stats;
    private List<Move>  moves1;
    private List<Move>  moves2;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Stats getStats() {
        return stats;
    }

    public List<Move> getMoves1() {
        return moves1;
    }

    public List<Move> getMoves2() {
        return moves2;
    }
}
