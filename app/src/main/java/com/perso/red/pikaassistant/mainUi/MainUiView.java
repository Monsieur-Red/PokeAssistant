package com.perso.red.pikaassistant.mainUi;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import com.perso.red.pikaassistant.R;
import com.perso.red.pikaassistant.MyService;
import com.perso.red.pikaassistant.models.ViewsSelected;
import com.perso.red.pikaassistant.utils.Constants;

import info.hoang8f.widget.FButton;
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
    private ViewGroup   buddiesView;
    private ViewGroup   xpCalculatorView;
    private ViewGroup   evoCalculatorView;

    private boolean blurBg = false;

    public MainUiView(MyService service, WindowManager windowManager, ViewsSelected viewsSelected) {
        context = service;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            blurBg = true;

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN,
                PixelFormat.TRANSLUCENT);

        if (viewsSelected.isIvCalculator()) {
            ivCalculatorView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_iv_calculator, null);
            ivCalculatorView.setVisibility(View.GONE);
            windowManager.addView(ivCalculatorView, params);
        }
        if (viewsSelected.isXpCalculator()) {
            xpCalculatorView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_experience_calculator, null);
            xpCalculatorView.setVisibility(View.GONE);
            windowManager.addView(xpCalculatorView, params);
        }
        if (viewsSelected.isEvolutionCalculator()) {
            evoCalculatorView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_evolution_calculator, null);
            evoCalculatorView.setVisibility(View.GONE);
            windowManager.addView(evoCalculatorView, params);
        }
        if (viewsSelected.isEggs()) {
            eggsView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_eggs, null);
            eggsView.setVisibility(View.GONE);
            windowManager.addView(eggsView, params);
        }
        if (viewsSelected.isBuddies()) {
            buddiesView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_buddies, null);
            buddiesView.setVisibility(View.GONE);
            windowManager.addView(buddiesView, params);
        }
        if (viewsSelected.isAppraisal()) {
            appraisalView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_appraisal, null);
            appraisalView.setVisibility(View.GONE);
            windowManager.addView(appraisalView, params);
        }
        if (viewsSelected.size() > 1) {
            int concreteColor = ContextCompat.getColor(service, info.hoang8f.fbutton.R.color.fbutton_color_concrete);
            int asbestosColor = ContextCompat.getColor(service, info.hoang8f.fbutton.R.color.fbutton_color_asbestos);

            menuView = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.view_menu, null);
            menuView.setVisibility(View.GONE);
            windowManager.addView(menuView, params);

            Button backBtn = (Button) menuView.findViewById(R.id.btn_back);
            FButton ivCalcBtn = (FButton) menuView.findViewById(R.id.btn_iv_calculator);
            FButton xpCalcBtn = (FButton) menuView.findViewById(R.id.btn_experience_calculator);
            FButton evoCalcBtn = (FButton) menuView.findViewById(R.id.btn_evolution_calculator);
            FButton eggsBtn = (FButton) menuView.findViewById(R.id.btn_eggs);
            FButton buddiesBtn = (FButton) menuView.findViewById(R.id.btn_buddies);
            FButton appraisalBtn = (FButton) menuView.findViewById(R.id.btn_appraisal);

            backBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.back();
                }
            });

            if (viewsSelected.isIvCalculator()) {
                ivCalcBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.showIvCalculator(ivCalculatorView);
                    }
                });
            } else {
                ivCalcBtn.setButtonColor(concreteColor);
                ivCalcBtn.setShadowColor(asbestosColor);
            }

            if (viewsSelected.isXpCalculator()) {
                xpCalcBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.showXPCalculator(xpCalculatorView);
                    }
                });
            } else {
                xpCalcBtn.setButtonColor(concreteColor);
                xpCalcBtn.setShadowColor(asbestosColor);
            }

            if (viewsSelected.isEvolutionCalculator()) {
                evoCalcBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.showEvoCalculator(evoCalculatorView);
                    }
                });
            } else {
                evoCalcBtn.setButtonColor(concreteColor);
                evoCalcBtn.setShadowColor(asbestosColor);
            }

            if (viewsSelected.isEggs()) {
                eggsBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.showEggs(eggsView);
                    }
                });
            } else {
                eggsBtn.setButtonColor(concreteColor);
                eggsBtn.setShadowColor(asbestosColor);
            }

            if (viewsSelected.isBuddies()) {
                buddiesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.showBuddies(buddiesView);
                    }
                });
            } else {
                buddiesBtn.setButtonColor(concreteColor);
                buddiesBtn.setShadowColor(asbestosColor);
            }

            if (viewsSelected.isAppraisal()) {
                appraisalBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        presenter.showAppraisal(appraisalView);
                    }
                });
            } else {
                appraisalBtn.setButtonColor(concreteColor);
                appraisalBtn.setShadowColor(asbestosColor);
            }
        }

            presenter = new MainUiPresenter(service, this, windowManager, viewsSelected);
    }

    @Override
    public void setVisibility() {
        presenter.setVisibility();
    }

    public ViewGroup getViewGroup(int id) {
        switch (id) {
            case Constants.VIEW_MENU:
                return menuView;
            case Constants.VIEW_IV_CALC:
                return ivCalculatorView;
            case Constants.VIEW_EGGS:
                return eggsView;
            case Constants.VIEW_BUDDIES:
                return buddiesView;
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
        if (ivCalculatorView != null)
            windowManager.removeView(ivCalculatorView);
        if (eggsView != null)
            windowManager.removeView(eggsView);
        if (buddiesView != null)
            windowManager.removeView(buddiesView);
        if (appraisalView != null)
            windowManager.removeView(appraisalView);
        if (xpCalculatorView != null)
            windowManager.removeView(xpCalculatorView);
        if (evoCalculatorView != null)
            windowManager.removeView(evoCalculatorView);
        if (menuView != null)
            windowManager.removeView(menuView);
        if (ivCalculatorView != null)
            windowManager.removeView(presenter.getMyArcPointer());
    }

    public String getTrainerLvl() {
        if (ivCalculatorView != null)
            return presenter.getTrainerLvl();
        return "";
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

    public ViewGroup getBuddiesView() {
        return buddiesView;
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
