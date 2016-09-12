package com.perso.red.pokeassistant.mainUi;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.appraisal.AppraisalView;
import com.perso.red.pokeassistant.ivcalculator.presenter.IVCalculatorPresenter;
import com.perso.red.pokeassistant.service.MyService;
import com.perso.red.pokeassistant.utils.Constants;

import butterknife.ButterKnife;
import butterknife.OnClick;
import jp.wasabeef.blurry.Blurry;

/**
 * Created by pierr on 19/08/2016.
 */

public class MainUiView implements MainUi.View {

    private Context         context;
    private MainUiPresenter presenter;

    private ViewGroup   menuView;
    private ViewGroup   ivCalculatorView;
    private ViewGroup   eggsView;
    private ViewGroup   appraisalView;
    private ViewGroup   xpCalculatorView;
    private ViewGroup   evoCalculatorView;

    private boolean blurBg = false;

    public MainUiView(MyService service, WindowManager windowManager) {
        context = service;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            blurBg = true;

        menuView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_menu, null);
        ivCalculatorView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_iv_calculator, null);
        eggsView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_eggs, null);
        appraisalView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_appraisal, null);
        xpCalculatorView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_experience_calculator, null);
        evoCalculatorView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_evolution_calculator, null);
        menuView.setVisibility(View.GONE);
        ivCalculatorView.setVisibility(View.GONE);
        eggsView.setVisibility(View.GONE);
        appraisalView.setVisibility(View.GONE);
        xpCalculatorView.setVisibility(View.GONE);
        evoCalculatorView.setVisibility(View.GONE);

        ButterKnife.bind(this, menuView);
        presenter = new MainUiPresenter(service, this, windowManager);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN,
                PixelFormat.TRANSLUCENT);

        windowManager.addView(ivCalculatorView, params);
        windowManager.addView(eggsView, params);
        windowManager.addView(appraisalView, params);
        windowManager.addView(xpCalculatorView, params);
        windowManager.addView(evoCalculatorView, params);
        windowManager.addView(menuView, params);
    }

    @Override
    public void setVisibility() {
        presenter.setVisibility();
    }

    @OnClick(R.id.btn_back)
    public void OnClickBack() {
        presenter.back();
    }

    @OnClick(R.id.btn_iv_calculator)
    public void OnClickIvCalculator() {
        presenter.showIvCalculator(ivCalculatorView);
    }

    @OnClick(R.id.btn_eggs)
    public void OnClickEggs() {
        presenter.showEggs(eggsView);
    }

    @OnClick(R.id.btn_appraisal)
    public void OnClickAppraisal() {
        presenter.showAppraisal(appraisalView);
    }

    @OnClick(R.id.btn_experience_calculator)
    public void OnClickXPCalculator() {
        presenter.showXPCalculator(xpCalculatorView);
    }

    @OnClick(R.id.btn_evolution_calculator)
    public void OnClickEvoCalculator() {
        presenter.showEvoCalculator(evoCalculatorView);
    }

    public ViewGroup getViewGroup(int id) {
        switch (id) {
            case Constants.VIEW_MENU:
                return menuView;
            case Constants.VIEW_IV_CALC:
                return ivCalculatorView;
            case Constants.VIEW_EGGS:
                return eggsView;
            case Constants.VIEW_APPRAISAL:
                return appraisalView;
            case Constants.VIEW_XP_CALC:
                return xpCalculatorView;
            case Constants.VIEW_EVO_CALC:
                return evoCalculatorView;
        }
        return null;
    }

    public void setupBlurView(ViewGroup viewGroup) {
        if (blurBg) {
            Blurry.with(context)
                    .radius(10)
                    .sampling(8)
                    .async()
                    .animate(200)
                    .onto(viewGroup);
        }
    }

    public void deleteBlurView(ViewGroup viewGroup) {
        if (blurBg)
            Blurry.delete(viewGroup);
    }

    @Override
    public void removeView(WindowManager windowManager) {
        windowManager.removeView(ivCalculatorView);
        windowManager.removeView(eggsView);
        windowManager.removeView(appraisalView);
        windowManager.removeView(xpCalculatorView);
        windowManager.removeView(evoCalculatorView);
        windowManager.removeView(menuView);
        windowManager.removeView(presenter.getMyArcPointer());
    }

    public String getTrainerLvl() {
        return presenter.getTrainerLvl();
    }

    public ViewGroup getMenuView() {
        return menuView;
    }

    public ViewGroup getIvCalculatorView() {
        return ivCalculatorView;
    }

    public ViewGroup getEggsView() {
        return eggsView;
    }

    public ViewGroup getAppraisalView() {
        return appraisalView;
    }

    public ViewGroup getXpCalculatorView() {
        return xpCalculatorView;
    }

    public ViewGroup getEvoCalculatorView() {
        return evoCalculatorView;
    }
}
