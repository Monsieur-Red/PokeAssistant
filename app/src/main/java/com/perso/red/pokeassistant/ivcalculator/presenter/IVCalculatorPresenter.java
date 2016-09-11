package com.perso.red.pokeassistant.ivcalculator.presenter;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.mainUi.MainUiPresenter;
import com.perso.red.pokeassistant.mainUi.MainUiView;
import com.perso.red.pokeassistant.models.IVCalculatorModel;
import com.perso.red.pokeassistant.ivcalculator.view.IVCalculatorView;
import com.perso.red.pokeassistant.ivdetails.presenter.IVDetailsPresenter;
import com.perso.red.pokeassistant.move.presenter.MovePresenter;
import com.perso.red.pokeassistant.utils.Tools;

import java.util.List;

/**
 * Created by pierr on 17/08/2016.
 */

public class IVCalculatorPresenter implements IIVCalculatorPresenter, IOnIVCalculatorFinishedListener {

    private IVCalculatorView        view;
    private IVCalculatorInteractor  interactor;

    private MainUiPresenter     mainUiPresenter;
    private IVDetailsPresenter  ivDetailsPresenter;
    private MovePresenter       movePresenter;

    public IVCalculatorPresenter(MainUiPresenter mainUiPresenter, View layout, WindowManager windowManager) {
        this.mainUiPresenter = mainUiPresenter;
        interactor = new IVCalculatorInteractor(layout.getContext().getAssets());
        view = new IVCalculatorView(layout, windowManager, this);
        ivDetailsPresenter = new IVDetailsPresenter(layout.findViewById(R.id.view_iv_details), interactor.getIvCalculatorModel());
        movePresenter = new MovePresenter(layout.findViewById(R.id.view_moves));
        checkTrainerLvlSave(layout.getContext());
    }

    private void checkTrainerLvlSave(Context context) {
        String  lvl = Tools.getTrainerLevel(context);

        if (!lvl.equals(context.getString(R.string.sp_default_value)))
            view.getTrainerLvl().setText(lvl);
    }

    @Override
    public void setTrainerLvl(String trainerLvl) {
        interactor.setTrainerLvl(Integer.valueOf(trainerLvl));

        if (view.isInputsFilled())
            ivDetailsPresenter.update();
    }

    @Override
    public void setPokemonName(String name) {
        interactor.setPokemonName(name, this);
        view.hidePokemonsNames(name);

        if (view.isInputsFilled())
            ivDetailsPresenter.update();
    }

    @Override
    public void setPokemonCP(String cp) {
        interactor.setPokemonCP(Integer.valueOf(cp));

        if (view.isInputsFilled())
            ivDetailsPresenter.update();
    }

    @Override
    public void setPokemonHP(String hp) {
        interactor.setPokemonHP(Integer.valueOf(hp));

        if (view.isInputsFilled())
            ivDetailsPresenter.update();
    }

    @Override
    public void setPokemonLvl(double lvl) {
        interactor.setPokemonLvl(lvl);

        if (view.isInputsFilled())
            ivDetailsPresenter.update();
    }

    @Override
    public void setPokemonDust(String dust) {
        interactor.setPokemonDust(dust);

        if (view.isInputsFilled())
            ivDetailsPresenter.update();
    }

    @Override
    public void setPokemonPoweredUp(boolean poweredUp) {
        interactor.setPokemonPoweredUp(poweredUp);

        if (view.isInputsFilled())
            ivDetailsPresenter.update();
    }

    @Override
    public void setCalculatorMode(int mode) {
        interactor.setCalculatorMode(mode);

        if (view.isInputsFilled())
            ivDetailsPresenter.update();
    }

    @Override
    public void showIvDetails() {
        movePresenter.getView().hide();
        ivDetailsPresenter.onClick(R.id.btn_iv_details);
    }

    @Override
    public void showMoves() {
        ivDetailsPresenter.getView().hide();
        movePresenter.onClick(R.id.btn_moves);
    }

    @Override
    public void setVisibility(int visibility) {
        view.setVisibility(visibility);
        view.getMyArcPointer().setVisibility(visibility);
    }

    @Override
    public void showMenu() {
        mainUiPresenter.showMenu();
    }

    public String getTrainerLvl() {
        return view.getTrainerLvl().getText().toString();
    }

    public IVCalculatorView getView() {
        return view;
    }

    public ImageView getMyArcPointer() {
        return view.getMyArcPointer();
    }

    public List<String> getPokemonNames() {
        return interactor.getPokemonNames();
    }

    @Override
    public void onSuccessSetNamePokemon(int id) {
        movePresenter.update(id);
    }
}
