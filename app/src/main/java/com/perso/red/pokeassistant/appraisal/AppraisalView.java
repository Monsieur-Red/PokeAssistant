package com.perso.red.pokeassistant.appraisal;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.perso.red.pokeassistant.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

/**
 * Created by pierr on 09/09/2016.
 */

public class AppraisalView implements Appraisal.View {

    private AppraisalPresenter presenter;

    @BindView(R.id.checkbox_instinct)
    CheckBox instinctCb;
    @BindView(R.id.checkbox_mystic)
    CheckBox mysticCb;
    @BindView(R.id.checkbox_valor)
    CheckBox valorCb;
    @BindView(R.id.checkboxes_analysis)
    LinearLayout analysisCheckboxes;
    @BindView(R.id.checkbox_iv_analysis)
    CheckBox ivAnalysisCheckbox;
    @BindView(R.id.checkbox_stat_analysis)
    CheckBox statAnalysisCheckbox;
    @BindView(R.id.checkbox_size_analysis)
    CheckBox sizeAnalysisCheckbox;
    @BindView(R.id.instinct_appraisal_iv_analysis)
    LinearLayout instinctIvAnalysis;
    @BindView(R.id.instinct_appraisal_stat_analysis)
    LinearLayout instinctStatAnalysis;
    @BindView(R.id.instinct_appraisal_size_analysis)
    LinearLayout instinctSizeAnalysis;
    @BindView(R.id.mystic_appraisal_iv_analysis)
    LinearLayout mysticIvAnalysis;
    @BindView(R.id.mystic_appraisal_stat_analysis)
    LinearLayout mysticStatAnalysis;
    @BindView(R.id.mystic_appraisal_size_analysis)
    LinearLayout mysticSizeAnalysis;
    @BindView(R.id.valor_appraisal_iv_analysis)
    LinearLayout valorIvAnalysis;
    @BindView(R.id.valor_appraisal_stat_analysis)
    LinearLayout valorStatAnalysis;
    @BindView(R.id.valor_appraisal_size_analysis)
    LinearLayout valorSizeAnalysis;


    public AppraisalView(View view, AppraisalPresenter presenter) {
        this.presenter = presenter;
        ButterKnife.bind(this, view);

        analysisCheckboxes.setVisibility(View.GONE);
        instinctIvAnalysis.setVisibility(View.GONE);
        instinctStatAnalysis.setVisibility(View.GONE);
        instinctSizeAnalysis.setVisibility(View.GONE);
        mysticIvAnalysis.setVisibility(View.GONE);
        mysticStatAnalysis.setVisibility(View.GONE);
        mysticSizeAnalysis.setVisibility(View.GONE);
        valorIvAnalysis.setVisibility(View.GONE);
        valorStatAnalysis.setVisibility(View.GONE);
        valorSizeAnalysis.setVisibility(View.GONE);
    }

    @OnClick(R.id.btn_menu)
    public void OnClickMenu() {
        presenter.showMenu();
    }

    @OnCheckedChanged(R.id.checkbox_instinct)
    public void OnCheckChangedInstinct(boolean isChecked) {
        if (isChecked || mysticCb.isChecked() || valorCb.isChecked())
            analysisCheckboxes.setVisibility(View.VISIBLE);
        else
            analysisCheckboxes.setVisibility(View.GONE);

        if (isChecked) {
            if (ivAnalysisCheckbox.isChecked())
                instinctIvAnalysis.setVisibility(View.VISIBLE);
            else
                instinctIvAnalysis.setVisibility(View.GONE);
            if (statAnalysisCheckbox.isChecked())
                instinctStatAnalysis.setVisibility(View.VISIBLE);
            else
                instinctStatAnalysis.setVisibility(View.GONE);
            if (sizeAnalysisCheckbox.isChecked())
                instinctSizeAnalysis.setVisibility(View.VISIBLE);
            else
                instinctSizeAnalysis.setVisibility(View.GONE);
        }
        else {
            instinctIvAnalysis.setVisibility(View.GONE);
            instinctStatAnalysis.setVisibility(View.GONE);
            instinctSizeAnalysis.setVisibility(View.GONE);
        }
    }

    @OnCheckedChanged(R.id.checkbox_mystic)
    public void OnCheckChangedMystic(boolean isChecked) {
        if (isChecked || instinctCb.isChecked() || valorCb.isChecked())
            analysisCheckboxes.setVisibility(View.VISIBLE);
        else
            analysisCheckboxes.setVisibility(View.GONE);

        if (isChecked) {
            if (ivAnalysisCheckbox.isChecked())
                mysticIvAnalysis.setVisibility(View.VISIBLE);
            else
                mysticIvAnalysis.setVisibility(View.GONE);
            if (statAnalysisCheckbox.isChecked())
                mysticStatAnalysis.setVisibility(View.VISIBLE);
            else
                mysticStatAnalysis.setVisibility(View.GONE);
            if (sizeAnalysisCheckbox.isChecked())
                mysticSizeAnalysis.setVisibility(View.VISIBLE);
            else
                mysticSizeAnalysis.setVisibility(View.GONE);
        }
        else {
            mysticIvAnalysis.setVisibility(View.GONE);
            mysticStatAnalysis.setVisibility(View.GONE);
            mysticSizeAnalysis.setVisibility(View.GONE);
        }
    }

    @OnCheckedChanged(R.id.checkbox_valor)
    public void OnCheckChangedValor(boolean isChecked) {
        if (isChecked || instinctCb.isChecked() || mysticCb.isChecked())
            analysisCheckboxes.setVisibility(View.VISIBLE);
        else
            analysisCheckboxes.setVisibility(View.GONE);

        if (isChecked) {
            if (ivAnalysisCheckbox.isChecked())
                valorIvAnalysis.setVisibility(View.VISIBLE);
            else
                valorIvAnalysis.setVisibility(View.GONE);
            if (statAnalysisCheckbox.isChecked())
                valorStatAnalysis.setVisibility(View.VISIBLE);
            else
                valorStatAnalysis.setVisibility(View.GONE);
            if (sizeAnalysisCheckbox.isChecked())
                valorSizeAnalysis.setVisibility(View.VISIBLE);
            else
                valorSizeAnalysis.setVisibility(View.GONE);
        }
        else {
            valorIvAnalysis.setVisibility(View.GONE);
            valorStatAnalysis.setVisibility(View.GONE);
            valorSizeAnalysis.setVisibility(View.GONE);
        }
    }

    @OnCheckedChanged(R.id.checkbox_iv_analysis)
    public void OnCheckChangedIvAnalysis(boolean isChecked) {
        if (isChecked && instinctCb.isChecked())
            instinctIvAnalysis.setVisibility(View.VISIBLE);
        else if (!isChecked && instinctCb.isChecked())
            instinctIvAnalysis.setVisibility(View.GONE);
        
        if (isChecked && mysticCb.isChecked())
            mysticIvAnalysis.setVisibility(View.VISIBLE);
        else if (!isChecked && mysticCb.isChecked())
            mysticIvAnalysis.setVisibility(View.GONE);

        if (isChecked && valorCb.isChecked())
            valorIvAnalysis.setVisibility(View.VISIBLE);
        else if (!isChecked && valorCb.isChecked())
            valorIvAnalysis.setVisibility(View.GONE);
    }

    @OnCheckedChanged(R.id.checkbox_stat_analysis)
    public void OnCheckChangedStatAnalysis(boolean isChecked) {
        if (isChecked && instinctCb.isChecked())
            instinctStatAnalysis.setVisibility(View.VISIBLE);
        else if (!isChecked && instinctCb.isChecked())
            instinctStatAnalysis.setVisibility(View.GONE);

        if (isChecked && mysticCb.isChecked())
            mysticStatAnalysis.setVisibility(View.VISIBLE);
        else if (!isChecked && mysticCb.isChecked())
            mysticStatAnalysis.setVisibility(View.GONE);

        if (isChecked && valorCb.isChecked())
            valorStatAnalysis.setVisibility(View.VISIBLE);
        else if (!isChecked && valorCb.isChecked())
            valorStatAnalysis.setVisibility(View.GONE);
    }

    @OnCheckedChanged(R.id.checkbox_size_analysis)
    public void OnCheckChangedSizeAnalysis(boolean isChecked) {
        if (isChecked && instinctCb.isChecked())
            instinctSizeAnalysis.setVisibility(View.VISIBLE);
        else if (!isChecked && instinctCb.isChecked())
            instinctSizeAnalysis.setVisibility(View.GONE);

        if (isChecked && mysticCb.isChecked())
            mysticSizeAnalysis.setVisibility(View.VISIBLE);
        else if (!isChecked && mysticCb.isChecked())
            mysticSizeAnalysis.setVisibility(View.GONE);

        if (isChecked && valorCb.isChecked())
            valorSizeAnalysis.setVisibility(View.VISIBLE);
        else if (!isChecked && valorCb.isChecked())
            valorSizeAnalysis.setVisibility(View.GONE);
    }
}
