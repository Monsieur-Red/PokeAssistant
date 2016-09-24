package com.perso.red.pikaassistant.mainUi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.perso.red.pikaassistant.appraisal.AppraisalPresenter;
import com.perso.red.pikaassistant.buddies.BuddiesPresenter;
import com.perso.red.pikaassistant.eggs.EggsPresenter;
import com.perso.red.pikaassistant.evocalculator.EvoCalculatorPresenter;
import com.perso.red.pikaassistant.ivcalculator.presenter.IVCalculatorPresenter;
import com.perso.red.pikaassistant.models.ModelManager;
import com.perso.red.pikaassistant.models.ViewsSelected;
import com.perso.red.pikaassistant.utils.Constants;
import com.perso.red.pikaassistant.xpcalculator.XPCalculatorPresenter;

/**
 * Created by pierr on 09/09/2016.
 */

public class MainUiPresenter implements MainUi.Presenter {

    private MainUiView              view;
    private IVCalculatorPresenter   ivCalculatorPresenter;

    private boolean display;
    private int     oldViewId;
    private int     currentViewId;

    public MainUiPresenter(Context context, MainUiView view, WindowManager windowManager, ViewsSelected viewsSelected) {
        this.view = view;
        ModelManager modelManager = new ModelManager(context.getAssets());

        if (viewsSelected.isIvCalculator())
            ivCalculatorPresenter = new IVCalculatorPresenter(this, view.getIvCalculatorView(), windowManager, modelManager, viewsSelected.size() > 1);
        if (viewsSelected.isXpCalculator())
            new XPCalculatorPresenter(this, view.getXpCalculatorView(), modelManager, viewsSelected.size() > 1);
        if (viewsSelected.isEvolutionCalculator())
            new EvoCalculatorPresenter(this, view.getEvoCalculatorView(), modelManager, viewsSelected.size() > 1);
        if (viewsSelected.isEggs())
            new EggsPresenter(this, view.getEggsView(), viewsSelected.size() > 1);
        if (viewsSelected.isBuddies())
            new BuddiesPresenter(this, view.getBuddiesView(), viewsSelected.size() > 1);
        if (viewsSelected.isAppraisal())
            new AppraisalPresenter(this, view.getAppraisalView(), viewsSelected.size() > 0);
        display = false;
        oldViewId = Constants.VIEW_NONE;

        if (viewsSelected.isIvCalculator())
            currentViewId = Constants.VIEW_IV_CALC;
        else if (viewsSelected.isXpCalculator())
            currentViewId = Constants.VIEW_XP_CALC;
        else if (viewsSelected.isEvolutionCalculator())
            currentViewId = Constants.VIEW_EVO_CALC;
        else if (viewsSelected.isXpCalculator())
            currentViewId = Constants.VIEW_XP_CALC;
        else if (viewsSelected.isEggs())
            currentViewId = Constants.VIEW_EGGS;
        else if (viewsSelected.isBuddies())
            currentViewId = Constants.VIEW_BUDDIES;
        else if (viewsSelected.isAppraisal())
            currentViewId = Constants.VIEW_APPRAISAL;
    }

    @Override
    public void setVisibility() {
        ViewGroup   currentView = view.getViewGroup(currentViewId);
        ViewGroup   oldView = view.getViewGroup(oldViewId);

        if (display) {
            display = false;
            currentView.setVisibility(View.GONE);

            if (oldViewId != Constants.VIEW_MENU && oldView != null)
                oldView.setVisibility(View.GONE);

            if (currentViewId == Constants.VIEW_IV_CALC || oldViewId == Constants.VIEW_IV_CALC)
                ivCalculatorPresenter.getMyArcPointer().setVisibility(View.GONE);
        }
        else {
            display = true;
            currentView.setVisibility(View.VISIBLE);

            if (oldViewId != Constants.VIEW_MENU && oldView != null)
                oldView.setVisibility(View.VISIBLE);

            if (currentViewId == Constants.VIEW_IV_CALC || oldViewId == Constants.VIEW_IV_CALC)
                ivCalculatorPresenter.getMyArcPointer().setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showMenu() {
        ViewGroup   currentView = view.getViewGroup(currentViewId);

        if (currentViewId != Constants.VIEW_MENU) {
            view.setupBlurView(currentView);
            view.getMenuView().setVisibility(View.VISIBLE);
            oldViewId = currentViewId;
            currentViewId = Constants.VIEW_MENU;
        }
    }

    @Override
    public void back() {
        ViewGroup   oldView = view.getViewGroup(oldViewId);

        view.deleteBlurView(oldView);
        view.getMenuView().setVisibility(View.GONE);
        currentViewId = oldViewId;
        oldViewId = Constants.VIEW_MENU;
    }

    @Override
    public void showIvCalculator(ViewGroup ivCalculatorView) {
        ViewGroup   oldView = view.getViewGroup(oldViewId);
        ViewGroup   currentView = view.getViewGroup(currentViewId);

        if (currentViewId != Constants.VIEW_IV_CALC) {
            if (currentViewId == Constants.VIEW_MENU)
                view.deleteBlurView(oldView);

            oldView.setVisibility(View.GONE);
            currentView.setVisibility(View.GONE);
            ivCalculatorView.setVisibility(View.VISIBLE);
            ivCalculatorPresenter.getMyArcPointer().setVisibility(View.VISIBLE);
            oldViewId = currentViewId;
            currentViewId = Constants.VIEW_IV_CALC;
        }
    }

    @Override
    public void showEggs(ViewGroup eggsView) {
        ViewGroup   oldView = view.getViewGroup(oldViewId);
        ViewGroup   currentView = view.getViewGroup(currentViewId);

        if (currentViewId != Constants.VIEW_EGGS) {
            if (currentViewId == Constants.VIEW_MENU)
                view.deleteBlurView(oldView);

            if (oldViewId == Constants.VIEW_IV_CALC)
                ivCalculatorPresenter.getMyArcPointer().setVisibility(View.GONE);
            oldView.setVisibility(View.GONE);
            currentView.setVisibility(View.GONE);
            eggsView.setVisibility(View.VISIBLE);
            oldViewId = currentViewId;
            currentViewId = Constants.VIEW_EGGS;
        }
    }

    @Override
    public void showBuddies(ViewGroup buddiesView) {
        ViewGroup   oldView = view.getViewGroup(oldViewId);
        ViewGroup   currentView = view.getViewGroup(currentViewId);

        if (currentViewId != Constants.VIEW_BUDDIES) {
            if (currentViewId == Constants.VIEW_MENU)
                view.deleteBlurView(oldView);

            if (oldViewId == Constants.VIEW_IV_CALC)
                ivCalculatorPresenter.getMyArcPointer().setVisibility(View.GONE);
            oldView.setVisibility(View.GONE);
            currentView.setVisibility(View.GONE);
            buddiesView.setVisibility(View.VISIBLE);
            oldViewId = currentViewId;
            currentViewId = Constants.VIEW_BUDDIES;
        }
    }

    @Override
    public void showAppraisal(ViewGroup appraisalView) {
        ViewGroup   oldView = view.getViewGroup(oldViewId);
        ViewGroup   currentView = view.getViewGroup(currentViewId);

        if (currentViewId != Constants.VIEW_APPRAISAL) {
            if (currentViewId == Constants.VIEW_MENU)
                view.deleteBlurView(oldView);

            if (oldViewId == Constants.VIEW_IV_CALC)
                ivCalculatorPresenter.getMyArcPointer().setVisibility(View.GONE);
            oldView.setVisibility(View.GONE);
            currentView.setVisibility(View.GONE);
            appraisalView.setVisibility(View.VISIBLE);
            oldViewId = currentViewId;
            currentViewId = Constants.VIEW_APPRAISAL;
        }
    }

    @Override
    public void showXPCalculator(ViewGroup xpCalculatorView) {
        ViewGroup   oldView = view.getViewGroup(oldViewId);
        ViewGroup   currentView = view.getViewGroup(currentViewId);

        if (currentViewId != Constants.VIEW_XP_CALC) {
            if (currentViewId == Constants.VIEW_MENU)
                view.deleteBlurView(oldView);

            if (oldViewId == Constants.VIEW_IV_CALC)
                ivCalculatorPresenter.getMyArcPointer().setVisibility(View.GONE);
            oldView.setVisibility(View.GONE);
            currentView.setVisibility(View.GONE);
            xpCalculatorView.setVisibility(View.VISIBLE);
            oldViewId = currentViewId;
            currentViewId = Constants.VIEW_XP_CALC;
        }
    }

    @Override
    public void showEvoCalculator(ViewGroup evoCalculatorView) {
        ViewGroup   oldView = view.getViewGroup(oldViewId);
        ViewGroup   currentView = view.getViewGroup(currentViewId);

        if (currentViewId != Constants.VIEW_EVO_CALC) {
            if (currentViewId == Constants.VIEW_MENU)
                view.deleteBlurView(oldView);

            if (oldViewId == Constants.VIEW_IV_CALC)
                ivCalculatorPresenter.getMyArcPointer().setVisibility(View.GONE);
            oldView.setVisibility(View.GONE);
            currentView.setVisibility(View.GONE);
            evoCalculatorView.setVisibility(View.VISIBLE);
            oldViewId = currentViewId;
            currentViewId = Constants.VIEW_EVO_CALC;
        }
    }

    public String getTrainerLvl() {
        return ivCalculatorPresenter.getTrainerLvl();
    }

    public ImageView getMyArcPointer() {
        return ivCalculatorPresenter.getMyArcPointer();
    }
}
