package com.perso.red.pikaassistant.move.view;

import com.perso.red.pikaassistant.models.Move;

import java.util.List;

/**
 * Created by pierr on 19/08/2016.
 */

public interface IMoveView {

    boolean isShown();

    void show();

    void hide();

    void updateData(List<Move> moveSet1, List<Move> moveSet2);

}
