package com.perso.red.pokeassistant.ivcalculator.presenter;

import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.perso.red.pokeassistant.R;
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

    private IVDetailsPresenter  ivDetailsPresenter;
    private MovePresenter       movePresenter;

    public IVCalculatorPresenter(View layout, WindowManager windowManager, IVDetailsPresenter ivDetailsPresenter, MovePresenter movePresenter, IVCalculatorModel data) {
        this.ivDetailsPresenter = ivDetailsPresenter;
        this.movePresenter = movePresenter;
        interactor = new IVCalculatorInteractor(data, layout.getContext().getAssets());
        view = new IVCalculatorView(layout, windowManager, this);
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
    public void setVisibility(int visibility) {
        view.setVisibility(visibility);
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
