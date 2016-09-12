package com.perso.red.pokeassistant.ivdetails.presenter;

/**
 * Created by pierr on 19/08/2016.
 */

public interface IIVDetailsPresenter {

    void onClick(int viewId);

    void onCheckedChanged(int viewId, boolean isChecked);

    void update(boolean manually);

    void filterIVs();
}
