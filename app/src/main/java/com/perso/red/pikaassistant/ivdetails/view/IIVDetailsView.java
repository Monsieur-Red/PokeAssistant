package com.perso.red.pikaassistant.ivdetails.view;

import com.perso.red.pikaassistant.models.IVResult;

import java.util.List;

/**
 * Created by pierr on 19/08/2016.
 */

public interface IIVDetailsView {

    boolean isShown();

    void show();

    void hide();

    void updateData(List<IVResult> ivResults);

    void setNoCombinaisonsTextVisibility(int visibility);
}
