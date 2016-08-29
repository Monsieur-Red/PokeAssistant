package com.perso.red.pokeassistant.models;

/**
 * Created by pierr on 22/08/2016.
 */

public class IVResult implements Comparable<IVResult> {

    private float totalIV;

    private int attackIV;
    private int defenseIV;
    private int staminaIV;

    public IVResult(int attackIV, int defenseIV, int staminaIV) {
        this.attackIV = attackIV;
        this.defenseIV = defenseIV;
        this.staminaIV = staminaIV;

        totalIV = ((float) attackIV + (float) defenseIV + (float) staminaIV) / 45 * 100;
    }

    @Override
    public int compareTo(IVResult another) {

        if (totalIV > another.getTotalIV())
            return -1;
        else if (totalIV < another.getTotalIV())
            return 1;

        return 0;
    }

    public int getAttackIV() {
        return attackIV;
    }

    public int getDefenseIV() {
        return defenseIV;
    }

    public int getStaminaIV() {
        return staminaIV;
    }

    public float getTotalIV() {
        return totalIV;
    }
}
