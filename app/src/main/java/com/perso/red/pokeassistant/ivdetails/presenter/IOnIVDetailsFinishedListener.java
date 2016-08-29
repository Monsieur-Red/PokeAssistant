package com.perso.red.pokeassistant.ivdetails.presenter;

import com.perso.red.pokeassistant.models.IVResult;

import java.util.List;

/**
 * Created by pierr on 22/08/2016.
 */

public interface IOnIVDetailsFinishedListener {

    void onSuccessGuessingIV(List<IVResult> ivResults);

    void onFailGuessingIV();

}
