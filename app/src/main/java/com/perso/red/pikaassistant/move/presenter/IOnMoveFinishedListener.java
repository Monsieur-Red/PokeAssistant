package com.perso.red.pikaassistant.move.presenter;

import com.perso.red.pikaassistant.models.Move;

import java.util.List;

/**
 * Created by pierr on 24/08/2016.
 */

public interface IOnMoveFinishedListener {

    void onSuccessGetMoveSets(List<Move> moveSet1, List<Move> moveSet2);
}
