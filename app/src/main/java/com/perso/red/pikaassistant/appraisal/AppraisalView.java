package com.perso.red.pikaassistant.appraisal;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import com.perso.red.pikaassistant.R;

/**
 * Created by pierr on 09/09/2016.
 */

public class AppraisalView implements Appraisal.View {

    public AppraisalView(View view, final AppraisalPresenter presenter, boolean showMenuBtn) {
        Button                menuBtn = (Button) view.findViewById(R.id.btn_menu);
        final CheckBox        instinctCb = (CheckBox) view.findViewById(R.id.checkbox_instinct);
        final CheckBox        mysticCb = (CheckBox) view.findViewById(R.id.checkbox_mystic);
        final CheckBox        valorCb = (CheckBox) view.findViewById(R.id.checkbox_valor);
        final LinearLayout    analysisCheckboxes = (LinearLayout) view.findViewById(R.id.checkboxes_analysis);
        final CheckBox        ivAnalysisCheckbox = (CheckBox) view.findViewById(R.id.checkbox_iv_analysis);
        final CheckBox        statAnalysisCheckbox = (CheckBox) view.findViewById(R.id.checkbox_stat_analysis);
        final CheckBox        sizeAnalysisCheckbox = (CheckBox) view.findViewById(R.id.checkbox_size_analysis);
        final LinearLayout    instinctIvAnalysis = (LinearLayout) view.findViewById(R.id.instinct_appraisal_iv_analysis);
        final LinearLayout    instinctStatAnalysis = (LinearLayout) view.findViewById(R.id.instinct_appraisal_stat_analysis);
        final LinearLayout    instinctSizeAnalysis = (LinearLayout) view.findViewById(R.id.instinct_appraisal_size_analysis);
        final LinearLayout    mysticIvAnalysis = (LinearLayout) view.findViewById(R.id.mystic_appraisal_iv_analysis);
        final LinearLayout    mysticStatAnalysis = (LinearLayout) view.findViewById(R.id.mystic_appraisal_stat_analysis);
        final LinearLayout    mysticSizeAnalysis = (LinearLayout) view.findViewById(R.id.mystic_appraisal_size_analysis);
        final LinearLayout    valorIvAnalysis = (LinearLayout) view.findViewById(R.id.valor_appraisal_iv_analysis);
        final LinearLayout    valorStatAnalysis = (LinearLayout) view.findViewById(R.id.valor_appraisal_stat_analysis);
        final LinearLayout    valorSizeAnalysis = (LinearLayout) view.findViewById(R.id.valor_appraisal_size_analysis);
        
        if (!showMenuBtn)
            menuBtn.setVisibility(View.INVISIBLE);
        else {
            menuBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    presenter.showMenu();
                }
            });
        }

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

        instinctCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
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
        });

        mysticCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
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
        });

        valorCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
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
        });

        ivAnalysisCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
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
        });

        statAnalysisCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
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
        });

        sizeAnalysisCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
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
        });
    }
}
