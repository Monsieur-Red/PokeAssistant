package com.perso.red.pokeassistant.ivdetails.presenter;

import android.view.View;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.models.IVCalculatorModel;
import com.perso.red.pokeassistant.models.IVResult;
import com.perso.red.pokeassistant.ivdetails.view.IVDetailsView;

import java.util.List;

/**
 * Created by pierr on 19/08/2016.
 */

public class IVDetailsPresenter implements IIVDetailsPresenter, IOnIVDetailsFinishedListener {

    private IVDetailsView       view;
    private IVDetailsInteractor interactor;

    public IVDetailsPresenter(View layout, IVCalculatorModel data) {
        view = new IVDetailsView(layout);
        interactor = new IVDetailsInteractor(data, layout.getContext().getAssets());
    }

    @Override
    public void onClick(int viewId) {
        switch (viewId) {
            case R.id.btn_iv_details:
                if (view.isShown())
                    view.hide();
                else
                    view.show();
                break;
        }
    }

    @Override
    public void update() {
        interactor.update(this);
    }

    public IVDetailsView getView() {
        return view;
    }

    @Override
    public void onSuccessGuessingIV(List<IVResult> ivResults) {
        view.setNoCombinaisonsTextVisibility(View.GONE);
        view.updateData(ivResults);
    }

    @Override
    public void onFailGuessingIV() {
        view.setNoCombinaisonsTextVisibility(View.VISIBLE);
    }
}
