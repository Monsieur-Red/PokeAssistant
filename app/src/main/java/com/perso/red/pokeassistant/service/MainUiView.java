package com.perso.red.pokeassistant.service;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.models.IVCalculatorModel;
import com.perso.red.pokeassistant.ivcalculator.presenter.IVCalculatorPresenter;
import com.perso.red.pokeassistant.ivdetails.presenter.IVDetailsPresenter;
import com.perso.red.pokeassistant.move.presenter.MovePresenter;

/**
 * Created by pierr on 19/08/2016.
 */

public class MainUiView extends RelativeLayout implements View.OnClickListener {

    private IVCalculatorPresenter   ivCalculatorPresenter;
    private IVDetailsPresenter      ivDetailsPresenter;
    private MovePresenter           movePresenter;

    public MainUiView(Context context) {
        super(context);
    }

    public MainUiView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MainUiView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void init(WindowManager windowManager) {
        inflate(getContext(), R.layout.view_main_ui, this);
        setVisibility(GONE);

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN,
                PixelFormat.TRANSLUCENT);

        windowManager.addView(this, params);

        IVCalculatorModel   ivCalculatorModel = new IVCalculatorModel();
        ivDetailsPresenter = new IVDetailsPresenter(findViewById(R.id.view_iv_details), ivCalculatorModel);
        movePresenter = new MovePresenter(findViewById(R.id.view_moves));
        ivCalculatorPresenter = new IVCalculatorPresenter(findViewById(R.id.view_iv_calculator), windowManager, ivDetailsPresenter, movePresenter, ivCalculatorModel);
        findViewById(R.id.btn_iv_details).setOnClickListener(this);
        findViewById(R.id.btn_moves).setOnClickListener(this);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if (ivCalculatorPresenter != null)
            ivCalculatorPresenter.setVisibility(visibility);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_iv_details:
                movePresenter.getView().hide();
                ivDetailsPresenter.onClick(R.id.btn_iv_details);
                break;
            case R.id.btn_moves:
                ivDetailsPresenter.getView().hide();
                movePresenter.onClick(R.id.btn_moves);
                break;
        }
    }

    public void removeView(WindowManager windowManager) {
        windowManager.removeView(this);
        windowManager.removeView(ivCalculatorPresenter.getMyArcPointer());
    }

    public String getTrainerLvl() {
        return ivCalculatorPresenter.getTrainerLvl();
    }

}
