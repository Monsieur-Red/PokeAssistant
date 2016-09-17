package com.perso.red.pikaassistant.ivdetails.presenter;

import android.view.View;

import com.perso.red.pikaassistant.R;
import com.perso.red.pikaassistant.ivcalculator.presenter.IVCalculatorPresenter;
import com.perso.red.pikaassistant.models.IVCalculatorModel;
import com.perso.red.pikaassistant.models.IVResult;
import com.perso.red.pikaassistant.ivdetails.view.IVDetailsView;
import java.util.Collections;
import java.util.List;

/**
 * Created by pierr on 19/08/2016.
 */

public class IVDetailsPresenter implements IIVDetailsPresenter, IOnIVDetailsFinishedListener {

    private IVDetailsView       view;
    private IVDetailsInteractor interactor;

    private IVCalculatorPresenter ivCalculatorPresenter;

    public IVDetailsPresenter(View layout, IVCalculatorPresenter ivCalculatorPresenter, IVCalculatorModel data) {
        this.ivCalculatorPresenter = ivCalculatorPresenter;
        view = new IVDetailsView(layout, this);
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
    public void onCheckedChanged(int viewId, boolean isChecked) {
        switch (viewId) {
            case R.id.checkbox_auto_calculate:
                interactor.setAutoCalcChecked(isChecked);
                ivCalculatorPresenter.setAutoCalcMode(isChecked);
                break;
            case R.id.checkbox_attack:
                interactor.setAttackCbChecked(isChecked);
                filterIVs();
                break;
            case R.id.checkbox_defense:
                interactor.setDefenseCbChecked(isChecked);
                filterIVs();
                break;
            case R.id.checkbox_stamina:
                interactor.setStaminaCbChecked(isChecked);
                filterIVs();
                break;
        }
    }

    @Override
    public void update(boolean manually) {
        if (manually)
            interactor.updateManually(this);
        else
            interactor.update(this);
    }

    @Override
    public void filterIVs() {
        List<IVResult> ivResults = interactor.getIvResultsFinal();

        ivResults = interactor.filterIVs(ivResults);

        if (ivResults.size() > 0) {
            Collections.sort(ivResults);
            onSuccessGuessingIV(ivResults);
        }
        else
            onFailGuessingIV();

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
