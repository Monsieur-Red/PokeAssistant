package com.perso.red.pokeassistant.ivdetails.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.perso.red.pokeassistant.R;
import com.perso.red.pokeassistant.ivdetails.presenter.IVDetailsPresenter;
import com.perso.red.pokeassistant.models.IVResult;

import java.util.List;
import java.util.Locale;

/**
 * Created by pierr on 19/08/2016.
 */

public class IVDetailsView implements IIVDetailsView {

    private View                view;

    private TextView        noCombinations;
    private LinearLayout    tabIVs;
    private LinearLayout    tabTitles;
    private TextView        minIV;
    private TextView        averageIV;
    private TextView        maxIV;
    private RecyclerView    recyclerView;

    private IVDetailsRVAdapter  adapter;

    public IVDetailsView(View view, final IVDetailsPresenter presenter) {
        this.view = view;

        final TextView leaderRA = (TextView) view.findViewById(R.id.text_view_team_leader_result_analysis);
        noCombinations = (TextView) view.findViewById(R.id.text_view_no_combinations);
        CheckBox autoCalculateCheckBox = (CheckBox) view.findViewById(R.id.checkbox_auto_calculate);
        CheckBox attackCheckBox = (CheckBox) view.findViewById(R.id.checkbox_attack);
        CheckBox defenseCheckBox = (CheckBox) view.findViewById(R.id.checkbox_defense);
        CheckBox staminaCheckBox = (CheckBox) view.findViewById(R.id.checkbox_stamina);
        tabIVs = (LinearLayout) view.findViewById(R.id.view_iv_details_tab_iv);
        tabTitles = (LinearLayout) view.findViewById(R.id.view_iv_details_tab_titles);
        minIV = (TextView) view.findViewById(R.id.text_view_iv_details_min);
        averageIV = (TextView) view.findViewById(R.id.text_view_iv_details_average);
        maxIV = (TextView) view.findViewById(R.id.text_view_iv_details_max);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        adapter = new IVDetailsRVAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        autoCalculateCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked)
                    leaderRA.setVisibility(View.VISIBLE);
                else
                    leaderRA.setVisibility(View.GONE);

                presenter.onCheckedChanged(compoundButton.getId(), isChecked);
            }
        });

        attackCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                presenter.onCheckedChanged(compoundButton.getId(), isChecked);
            }
        });

        defenseCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                presenter.onCheckedChanged(compoundButton.getId(), isChecked);
            }
        });

        staminaCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                presenter.onCheckedChanged(compoundButton.getId(), isChecked);
            }
        });
    }

    @Override
    public boolean isShown() {
        return view.isShown();
    }

    @Override
    public void show() {
        view.setVisibility(View.VISIBLE);
    }

    @Override
    public void hide() {
        view.setVisibility(View.INVISIBLE);
    }

    @Override
    public void updateData(List<IVResult> ivResults) {
        double  minValue = ivResults.get(ivResults.size() - 1).getTotalIV();
        double  maxValue = ivResults.get(0).getTotalIV();
        double  averageValue = 0;

        for (IVResult ivResult : ivResults) {
            averageValue += ivResult.getTotalIV();
        }
        averageValue /= ivResults.size();

        String  min = String.format(Locale.getDefault(), "%.1f",minValue) + "%";
        String  max = String.format(Locale.getDefault(), "%.1f", maxValue) + "%";
        String  average = String.format(Locale.getDefault(), "%.1f", averageValue) + "%";

        minIV.setText(min);
        maxIV.setText(max);
        averageIV.setText(average);
        adapter.update(ivResults);
    }

    @Override
    public void setNoCombinaisonsTextVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            noCombinations.setVisibility(visibility);
            tabIVs.setVisibility(View.GONE);
            tabTitles.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
        }
        else {
            noCombinations.setVisibility(visibility);
            tabIVs.setVisibility(View.VISIBLE);
            tabTitles.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
