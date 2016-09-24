package com.perso.red.pikaassistant.move.presenter;

import android.view.View;

import com.perso.red.pikaassistant.R;
import com.perso.red.pikaassistant.models.ModelManager;
import com.perso.red.pikaassistant.models.Move;
import com.perso.red.pikaassistant.move.view.MoveView;

import java.util.List;

/**
 * Created by pierr on 19/08/2016.
 */

public class MovePresenter implements IMovePresenter, IOnMoveFinishedListener {

    private MoveView        view;
    private MoveInteractor  interactor;

    public MovePresenter(View layout, ModelManager modelManager) {
        view = new MoveView(layout);
        interactor = new MoveInteractor(modelManager);
    }

    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.btn_moves:
                if (view.isShown())
                    view.hide();
                else
                    view.show();
                break;
        }
    }

    @Override
    public void update(int id) {
        interactor.getMoveSets(id, this);
    }

    public MoveView getView() {
        return view;
    }

    @Override
    public void onSuccessGetMoveSets(List<Move> moveSet1, List<Move> moveSet2) {
        view.updateData(moveSet1, moveSet2);
    }
}
