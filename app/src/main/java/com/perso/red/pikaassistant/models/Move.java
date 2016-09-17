package com.perso.red.pikaassistant.models;

import com.squareup.moshi.Json;

/**
 * Created by pierr on 24/08/2016.
 */

public class Move {

    @Json(name = "Id")          private int     id;
    @Json(name = "Name")        private String  name;
    @Json(name = "Type")        private String  type;
    @Json(name = "Power")       private int     power;
    @Json(name = "DurationMs")  private double  durationMs;
    @Json(name = "Energy")      private int     energy;
    @Json(name = "DPS")         private Double  dps;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getPower() {
        return power;
    }

    public double getDurationMs() {
        return durationMs;
    }

    public int getEnergy() {
        return energy;
    }

    public Double getDps() {
        return dps;
    }
}
