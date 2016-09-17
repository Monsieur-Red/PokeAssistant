package com.perso.red.pikaassistant.ivdetails.presenter;

import com.perso.red.pikaassistant.models.IVResult;

import java.util.List;

/**
 * Created by pierr on 22/08/2016.
 */

public interface IOnIVDetailsFinishedListener {

    void onSuccessGuessingIV(List<IVResult> ivResults);

    void onFailGuessingIV();

}
