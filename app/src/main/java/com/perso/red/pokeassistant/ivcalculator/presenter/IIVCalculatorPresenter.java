package com.perso.red.pokeassistant.ivcalculator.presenter;

/**
 * Created by pierr on 17/08/2016.
 */

public interface IIVCalculatorPresenter {

    void setTrainerLvl(String trainerLvl);

    void setPokemonName(String name);

    void setPokemonCP(String cp);

    void setPokemonHP(String hp);

    void setPokemonLvl(double lvl);

    void setPokemonDust(String dust);

    void setPokemonPoweredUp(boolean poweredUp);

    void setCalculatorMode(int mode);

    void showIvDetails();

    void showMoves();

    void setVisibility(int visibility);

    void showMenu();
}
